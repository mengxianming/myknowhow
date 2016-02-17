本工具可实现：
1. 读取Excel文件中表格、并输出。
2. 读取Excel文件中表格、并输出成sql语句。
3. 读取Excel文件中表格、并输出成java的enum类。

运行方法：
在命令行下运行start.cmd
1. 不输入参数、则输出到stdout
2. 若输入参数、如
   start.cmd myout.txt
   则输出到指定的文件myout.txt。

运行配置：
运行时需要配置config.json文件。不同功能的配置项有所不同、下面分别说明。

1. 读取Excel文件中表格、并输出。
配置如下：
{
    "excelFilePath": "D:/mengxm/账单详细类型管理表.xls",  //待解析的excel文件
    "sheetNum": 3,    //表格所在的sheet的index
    "startRowNum": 3, //表格开始的行、1表示第一行
    "startColNum": 2, //表格开始的列、1表示第一列
    "maxColCount": null, //最多读取表格的列数、若不指定则程序自动判定
    "mode": 0  //功能模式。0：只输出读取内容、1：生成sql、2: 生成java的enum类
}


2. 读取Excel文件中表格、并输出成sql语句。
配置如下：
{
    "excelFilePath": "D:/mengxm/账单详细类型管理表.xls",
    "sheetNum": 1,
    "startRowNum": 3,
    "startColNum": 2,
    "maxColCount": 4,
    "mode": 1,
    "sqlGenOpt": {
        "tableName": "acct.acct_billdtltype" //生成sql时需要制定该表格对应的db表名
    }
}

3. 读取Excel文件中表格、并输出成java的enum类。
配置如下：
{
    "excelFilePath": "D:/mengxm/账单详细类型管理表.xls",
    "sheetNum": 1,
    "startRowNum": 3,
    "startColNum": 2,
    "maxColCount": null,
    "mode": 2,
    "sqlGenOpt": null,
    "enumGenOpt": {
        "pkgName": "my.study",		//生成enum类所属pkg
        "className": "BusiTypeEnum",	//生成enum类名
        "keyIdx": 0,			//enum类中常量的idex属性位于表格中的第几列(相对于startColNum参数)
        "valIdx": 1,			//enum类中常量的desc属性位于表格中的第几列(相对于startColNum参数)
        "enumItemNameIdx": 2		//enum类中常量的名字位于表格中的第几列(相对于startColNum参数)
    }
}



 