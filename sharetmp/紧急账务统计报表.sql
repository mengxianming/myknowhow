/*1.8 房屋租赁交易数据*/
SELECT
abr.`doneCode` AS '交易流水号',
abr.`createTime` AS '交易时间',
abfd.`amount` AS '交易金额',
IF(abfd.`fundChannel`>100 AND abfd.`fundChannel`<200, '线下支付', '线上支付') AS '付款标识',
so.id AS '签约流水号',
ff.`flatsNum` AS '房屋挂牌唯一识别号',
fr.`id` AS '房间唯一识别号',
ff.`id` AS '房屋唯一识别号',
so.`renterId` AS '签约人ID',
(SELECT GROUP_CONCAT(scu.`userId`) FROM `cntr_salecontractuserrel` scu WHERE scu.`contractId`= so.`saleContractId`) AS '入住人ID',
IF(so.`status`=5 AND DATE(so.`checkOutTime`)<DATE(sc.`endDate`), 1, 0) AS '提前解约标识',
so.`landlordId` '职业房东ID',
ul.salesmanId AS '拓展员ID',
dict.`value` AS 'APP来源'
FROM acct.`acct_busirec` abr
INNER JOIN acct.`acct_busifeedtl` abfd ON abfd.`doneCode`=abr.`doneCode` AND abfd.`amount`>0
INNER JOIN acct.`acct_busibilldtl` abbd ON abbd.`doneCode`=abr.`doneCode`
INNER JOIN acct.`acct_bill` ab ON abbd.`billId`=ab.`billId` AND ab.`billType` IN (1003)
INNER JOIN bill_account_mapping bam ON bam.`billId`=ab.`billId`
INNER JOIN bill_salebill sb ON sb.`id`=bam.`orderId`
LEFT JOIN oder_signedorder so ON so.`id`=sb.`signedOrderId`
LEFT JOIN user_landlord ul ON so.landlordId=ul.id
LEFT JOIN `cntr_salecontract` sc ON sc.`id`=so.`saleContractId`
LEFT JOIN flat_room fr ON fr.`id`=so.`roomId`
LEFT JOIN flat_flats ff ON ff.`id`=fr.`flatsId`
LEFT JOIN user_renter ur ON so.`renterId`=ur.`id`
LEFT JOIN comm_dictionary dict ON dict.`groupName`='regChannelDtl' AND dict.`keyPro`=ur.`regChannelDtl`
WHERE abr.`createTime`<'2016-06-01';

/*1.9 租期内水电煤信息*/
SELECT 
so.id AS '签约流水号',
so.`renterId` AS '签约人ID',
so.`landlordId` '职业房东ID',
abdt.billDtlType '支付项目编号',
abdt.`billDtlName` AS '支付项目',
abd.`amount` AS '支付金额',
abr.`createTime` AS '支付日期',
CASE WHEN abfd.`fundChannel`>100 AND abfd.`fundChannel`<200 THEN '线下支付'
WHEN abfd.`fundChannel`=2 THEN '支付宝'
WHEN abfd.`fundChannel`=3 THEN '微信'
ELSE '其他' END '支付方式',
ur.realName '支付人'
FROM acct.`acct_busirec` abr
INNER JOIN acct.`acct_busifeedtl` abfd ON abfd.`doneCode`=abr.`doneCode` AND abfd.`amount`>0
INNER JOIN acct.`acct_busibilldtl` abbd ON abbd.`doneCode`=abr.`doneCode`
INNER JOIN acct.`acct_bill` ab ON abbd.`billId`=ab.`billId` AND ab.`billType` IN (1005, 20001)
INNER JOIN acct.`acct_billdtl` abd ON abd.`billId`=ab.`billId` AND abd.billDtlType IN (100006, 100007, 100008)
INNER JOIN acct.`acct_billdtltype` abdt ON abdt.`billDtlType`=abd.`billDtlType`
INNER JOIN bill_account_mapping bam ON bam.`billId`=ab.`billId`
INNER JOIN bill_salebill sb ON sb.`id`=bam.`orderId`
LEFT JOIN oder_signedorder so ON so.`id`=sb.`signedOrderId`
LEFT JOIN user_renter ur ON ur.id=so.renterId
WHERE abr.`createTime`<'2016-06-01' ;


/*1.10 付款记录（明细）数据*/
SELECT 
so.id AS '签约流水号',
so.`renterId` AS '签约人ID',
so.`landlordId` '职业房东ID',
abfd.`amount` AS '支付金额',
abr.`createTime` AS '支付日期',
CASE WHEN abfd.`fundChannel`>100 AND abfd.`fundChannel`<200 THEN '线下支付'
WHEN abfd.`fundChannel`=2 THEN '支付宝'
WHEN abfd.`fundChannel`=3 THEN '微信'
ELSE '其他' END '支付方式',
ur.realName '支付人'
FROM acct.`acct_busirec` abr
INNER JOIN acct.`acct_busifeedtl` abfd ON abfd.`doneCode`=abr.`doneCode` AND abfd.`amount`>0
INNER JOIN acct.`acct_busibilldtl` abbd ON abbd.`doneCode`=abr.`doneCode`
INNER JOIN acct.`acct_bill` ab ON abbd.`billId`=ab.`billId` AND ab.`billType` IN (1003, 1004, 1005, 10003, 20001)
INNER JOIN bill_account_mapping bam ON bam.`billId`=ab.`billId`
INNER JOIN bill_salebill sb ON sb.`id`=bam.`orderId`
LEFT JOIN oder_signedorder so ON so.`id`=sb.`signedOrderId`
LEFT JOIN user_renter ur ON ur.id=so.renterId
WHERE abr.`createTime`<'2016-06-01' ;


-- 查询201506月底到201605月底，每个月底的空置房间价格
select 
tmp.roomId '房间id',
tmp.checkTime '检查时间',
tmp.salePrice '销售价格'
from(
select 
tmp1.roomId,
tmp1.checkTime,
tmp1.defPrice,
ifnull(tmp2.amount, tmp1.defPrice) salePrice,
tmp2.amount,
tmp2.startTime,
tmp2.endTime
 from (
	select 
	fr.id roomId,
	ct.checkTime,
	(
	 select  rpd.amount from
	flat_roomprice rp
	join flat_roompricedtl rpd on rpd.priceId = rp.id
	join flat_roompricebiztype bt on bt.bizType = rp.bizType
	join comm_paytype pt on pt.id = bt.payTypeId
	where pt.payTypeGroup='1'
	and bt.payStage=3
	and rpd.billDtlType=10002
	and rp.goodsIdType=1 and rp.goodsId=fr.id
	order by rp.startTime limit 1
	) defPrice 
	from flat_room fr
	join flat_flats ff on fr.flatsId=ff.id
	join (
	select '2015-06-30' checkTime
	union select '2015-07-31' checkTime
	union select '2015-08-31' checkTime
	union select '2015-09-30' checkTime
	union select '2015-10-31' checkTime
	union select '2015-11-30' checkTime
	union select '2015-12-31' checkTime
	union select '2016-01-31' checkTime
	union select '2016-02-29' checkTime
	union select '2016-03-31' checkTime
	union select '2016-04-30' checkTime
	union select '2016-05-31' checkTime
	) ct on 1=1 
	where
	ff.rentType=1
	and not exists (
	select * from
	flat_room fr2 join flat_flats ff2 on fr2.flatsId=ff2.id
	join oder_signedorder oso on fr2.id=oso.roomId
	join cntr_salecontract csc on csc.id=oso.saleContractId
	where ff2.rentType=1 and fr.id=fr2.id and ct.checkTime>=date(csc.beginDate) and ct.checkTime<=date(ifnull(csc.loseEfficacyDate, csc.endDate))
	)
) tmp1
left join(
	select rp.goodsId roomId,
	pt.payTypeGroup payTypeGroup,
	ct.checkTime ,
	rpd.amount ,
	rp.startTime,
	rp.endTime
	from flat_roomprice rp 
	join flat_roompricedtl rpd on rpd.priceId = rp.id
	join flat_roompricebiztype bt on bt.bizType = rp.bizType
	join comm_paytype pt on pt.id=bt.payTypeId
	join (
	select '2015-06-30' checkTime
	union select '2015-07-31' checkTime
	union select '2015-08-31' checkTime
	union select '2015-09-30' checkTime
	union select '2015-10-31' checkTime
	union select '2015-11-30' checkTime
	union select '2015-12-31' checkTime
	union select '2016-01-31' checkTime
	union select '2016-02-29' checkTime
	union select '2016-03-31' checkTime
	union select '2016-04-30' checkTime
	union select '2016-05-31' checkTime
	) ct on rp.startTime <= ct.checkTime and (rp.endTime > ct.checkTime or rp.endTime is null)
	where
	rp.goodsIdType=1
	and bt.payStage=3
	and rpd.billDtlType=10002
) tmp2 on tmp1.roomId=tmp2.roomId and tmp1.checkTime=tmp2.checkTime
order by tmp1.roomId, tmp1.checkTime, tmp2.amount
) tmp
group by tmp.roomId, tmp.checkTime;


-- 从201506月底到201605月底，每个月底的逾期租金账单
select 
oso.roomId '房间id',
csc.realRentPrice '月租',
sb.amount '账单金额',
sb.dueDate '应交日期',
sb.payTime '最终交款日期',
ct.checkTime '检查日期'
from 
bill_salebill sb 
join oder_signedorder oso on sb.signedOrderId=oso.id
join cntr_salecontract csc on csc.id=oso.saleContractId
join flat_room fr on fr.id=oso.roomId
join flat_flats ff on fr.flatsId=ff.id
join (
select '2015-06-30' checkTime
union select '2015-07-31' checkTime
union select '2015-08-31' checkTime
union select '2015-09-30' checkTime
union select '2015-10-31' checkTime
union select '2015-11-30' checkTime
union select '2015-12-31' checkTime
union select '2016-01-31' checkTime
union select '2016-02-29' checkTime
union select '2016-03-31' checkTime
union select '2016-04-30' checkTime
union select '2016-05-31' checkTime
) ct on last_day(sb.dueDate) = last_day(ct.checkTime) and date(sb.dueDate) < date(sb.payTime)
where sb.bill_type in (1004, 10003)
and ff.rentType=1
order by oso.roomId, ct.checkTime;
