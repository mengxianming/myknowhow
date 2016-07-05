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
