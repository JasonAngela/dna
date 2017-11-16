SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS SPECIMEN_MATERIAL_REGISTER_ITEM;
DROP TABLE IF EXISTS ENTRUST_ABSTRACTS;
DROP TABLE IF EXISTS SPECIMEN_MATERIAL_IN_ITEM;
DROP TABLE IF EXISTS SPECIMEN_MATERIAL_IN;
DROP TABLE IF EXISTS SPECIMEN_MATERIAL_OUT_ITEM;
DROP TABLE IF EXISTS SPECIMEN_MATERIAL_OUT;
DROP TABLE IF EXISTS SPECIMEN_MATERIAL_REGISTER;
DROP TABLE IF EXISTS ENTRUST_REGISTER;




/* Create Tables */

CREATE TABLE ENTRUST_ABSTRACTS
(
	ID VARCHAR(64) NOT NULL,
	REGISTER_ID VARCHAR(64) NOT NULL,
	-- 编码
	CLIENT_CODE VARCHAR(255) COMMENT '编码',
	-- 名称
	CLIENT_NAME VARCHAR(255) COMMENT '名称',
	-- 性别
	GENDER CHAR COMMENT '性别',
	-- 称谓
	APPELLATION CHAR COMMENT '称谓',
	-- 出生日期
	BIRTHDAY VARCHAR(255) COMMENT '出生日期',
	-- 证件类型
	ID_TYPE CHAR COMMENT '证件类型',
	-- 证件号
	ID_NO VARCHAR(255) COMMENT '证件号',
	-- 证件图片
	ID_PIC VARCHAR(255) COMMENT '证件图片',
	-- 图片
	CLIENT_PIC VARCHAR(255) COMMENT '图片',
	-- 创建者
	CREATE_BY VARCHAR(64) COMMENT '创建者',
	-- 创建时间
	CREATE_DATE DATETIME COMMENT '创建时间',
	-- 修改者
	UPDATE_BY VARCHAR(64) COMMENT '修改者',
	-- 修改时间
	UPDATE_DATE DATETIME COMMENT '修改时间',
	-- 备注
	REMARKS VARCHAR(255) COMMENT '备注',
	-- 删除标记
	DEL_FLAG CHAR COMMENT '删除标记',
	PRIMARY KEY (ID)
);


-- 委托登记
CREATE TABLE ENTRUST_REGISTER
(
	ID VARCHAR(64) NOT NULL,
	-- 编码
	CODE VARCHAR(255) COMMENT '编码',
	-- 委托人
	CLIENT_NAME VARCHAR(255) COMMENT '委托人',
	-- 委托人电话
	CLIENT_TEL VARCHAR(255) COMMENT '委托人电话',
	-- 委托收件人
	CLIENT_RECEIVER VARCHAR(255) COMMENT '委托收件人',
	-- 委托人邮箱
	CLIENT_EMAIL VARCHAR(255) COMMENT '委托人邮箱',
	-- 委托人传真
	CLIENT_FAX VARCHAR(255) COMMENT '委托人传真',
	-- 委托人邮编
	CLIENT_ZIPCODE VARCHAR(255) COMMENT '委托人邮编',
	-- 委托人区域
	CLIENT_AREA VARCHAR(1000) COMMENT '委托人区域',
	-- 委托人地址
	CLIENT_ADDRESS VARCHAR(1000) COMMENT '委托人地址',
	-- 送检人(机构)
	AGENT_NAME VARCHAR(255) COMMENT '送检人(机构)',
	-- 送检人电话
	AGENT_TEL VARCHAR(255) COMMENT '送检人电话',
	-- 受理人
	SERVER_NAME VARCHAR(255) COMMENT '受理人',
	-- 受理机构
	SERVER_ORG_ID VARCHAR(255) COMMENT '受理机构',
	-- 报告传递方式
	SEND_MODE CHAR COMMENT '报告传递方式',
	-- 专业
	SPECIALTY CHAR COMMENT '专业',
	-- 类型
	TYPE CHAR COMMENT '类型',
	-- 鉴定材料
	MATERIAL VARCHAR(255) COMMENT '鉴定材料',
	-- 检材处理
	MATERIAL_DISPOSE VARCHAR(255) COMMENT '检材处理',
	-- 时限结果
	TIME_LIMIT_RESULT CHAR COMMENT '时限结果',
	-- 时间报告
	TIME_LIMIT_REPORT CHAR COMMENT '时间报告',
	-- 标准费用
	STANDARD_FEE REAL COMMENT '标准费用',
	-- 特殊费用
	SPECIAL_FEE REAL COMMENT '特殊费用',
	-- 合计费用
	TOTAL_FEE REAL COMMENT '合计费用',
	-- 鉴定项
	APPRAISAL_ITEM VARCHAR(255) COMMENT '鉴定项',
	-- 鉴定人回避
	CLIENT_AVOID BIT(1) COMMENT '鉴定人回避',
	-- 授权客服人员通知
	AUTHORIZE_NOTIFICATION BIT(1) COMMENT '授权客服人员通知',
	STATUS CHAR,
	-- 流程id
	PROCESS_INSTANCE_ID VARCHAR(255) COMMENT '流程id',
	-- 创建者
	CREATE_BY VARCHAR(64) COMMENT '创建者',
	-- 创建时间
	CREATE_DATE DATETIME COMMENT '创建时间',
	-- 修改者
	UPDATE_BY VARCHAR(64) COMMENT '修改者',
	-- 修改时间
	UPDATE_DATE DATETIME COMMENT '修改时间',
	-- 备注
	REMARKS VARCHAR(255) COMMENT '备注',
	-- 删除标记
	DEL_FLAG CHAR COMMENT '删除标记',
	PRIMARY KEY (ID),
	UNIQUE (CODE)
) COMMENT = '委托登记';


CREATE TABLE SPECIMEN_MATERIAL_IN
(
	ID VARCHAR(64) NOT NULL,
	MATERIAL_REGISTER_ID VARCHAR(64) NOT NULL,
	-- 编码
	CODE VARCHAR(255) COMMENT '编码',
	-- 图片
	PIC VARCHAR(255) COMMENT '图片',
	-- 子项数量
	ITEM_COUNT INT COMMENT '子项数量',
	-- 总量
	TOTAL_QTY INT COMMENT '总量',
	-- 状态
	STATUS CHAR COMMENT '状态',
	-- 创建者
	CREATE_BY VARCHAR(64) COMMENT '创建者',
	-- 创建时间
	CREATE_DATE DATETIME COMMENT '创建时间',
	-- 修改者
	UPDATE_BY VARCHAR(64) COMMENT '修改者',
	-- 修改时间
	UPDATE_DATE DATETIME COMMENT '修改时间',
	-- 备注
	REMARKS VARCHAR(255) COMMENT '备注',
	-- 删除标记
	DEL_FLAG CHAR COMMENT '删除标记',
	PRIMARY KEY (ID),
	UNIQUE (CODE)
);


CREATE TABLE SPECIMEN_MATERIAL_IN_ITEM
(
	ID VARCHAR(64) NOT NULL,
	MATERIAL_IN_ID VARCHAR(64) NOT NULL,
	-- 摘要id
	ABSTRACTS_ID VARCHAR(64) NOT NULL COMMENT '摘要id',
	-- 编码
	CODE VARCHAR(255) COMMENT '编码',
	CLIENT_CODE VARCHAR(255),
	MATERIAL_TYPE CHAR,
	QTY INT,
	-- 度量
	MEASURE VARCHAR(255) COMMENT '度量',
	PRIMARY KEY (ID)
);


CREATE TABLE SPECIMEN_MATERIAL_OUT
(
	ID VARCHAR(64) NOT NULL,
	MATERIAL_REGISTER_ID VARCHAR(64) NOT NULL,
	-- 编码
	CODE VARCHAR(255) COMMENT '编码',
	-- 图片
	PIC VARCHAR(255) COMMENT '图片',
	-- 子项数量
	ITEM_COUNT INT COMMENT '子项数量',
	-- 总量
	TOTAL_QTY INT COMMENT '总量',
	-- 状态
	STATUS CHAR COMMENT '状态',
	-- 创建者
	CREATE_BY VARCHAR(64) COMMENT '创建者',
	-- 创建时间
	CREATE_DATE DATETIME COMMENT '创建时间',
	-- 修改者
	UPDATE_BY VARCHAR(64) COMMENT '修改者',
	-- 修改时间
	UPDATE_DATE DATETIME COMMENT '修改时间',
	-- 备注
	REMARKS VARCHAR(255) COMMENT '备注',
	-- 删除标记
	DEL_FLAG CHAR COMMENT '删除标记',
	PRIMARY KEY (ID),
	UNIQUE (CODE)
);


CREATE TABLE SPECIMEN_MATERIAL_OUT_ITEM
(
	ID VARCHAR(64) NOT NULL,
	MATERIAL_OUT_ID VARCHAR(64) NOT NULL,
	-- 摘要id
	ABSTRACTS_ID VARCHAR(64) COMMENT '摘要id',
	-- 编码
	CODE VARCHAR(255) COMMENT '编码',
	CLIENT_CODE VARCHAR(255),
	MATERIAL_TYPE CHAR,
	QTY INT,
	-- 度量
	MEASURE VARCHAR(255) COMMENT '度量',
	PRIMARY KEY (ID)
);


CREATE TABLE SPECIMEN_MATERIAL_REGISTER
(
	ID VARCHAR(64) NOT NULL,
	REGISTER_ID VARCHAR(64) NOT NULL,
	-- 编码
	CODE VARCHAR(255) COMMENT '编码',
	-- 图片
	PIC VARCHAR(255) COMMENT '图片',
	-- 子项数量
	ITEM_COUNT INT COMMENT '子项数量',
	-- 总量
	TOTAL_QTY INT COMMENT '总量',
	-- 创建者
	CREATE_BY VARCHAR(64) COMMENT '创建者',
	-- 创建时间
	CREATE_DATE DATETIME COMMENT '创建时间',
	-- 修改者
	UPDATE_BY VARCHAR(64) COMMENT '修改者',
	-- 修改时间
	UPDATE_DATE DATETIME COMMENT '修改时间',
	-- 备注
	REMARKS VARCHAR(255) COMMENT '备注',
	-- 删除标记
	DEL_FLAG CHAR COMMENT '删除标记',
	PRIMARY KEY (ID),
	UNIQUE (CODE)
);


CREATE TABLE SPECIMEN_MATERIAL_REGISTER_ITEM
(
	ID VARCHAR(64) NOT NULL,
	MATERIAL_REGISTER_ID VARCHAR(64) NOT NULL,
	ABSTRACTS VARCHAR(64) NOT NULL,
	-- 编码
	CODE VARCHAR(255) COMMENT '编码',
	CLIENT_CODE VARCHAR(255),
	MATERIAL_TYPE CHAR,
	QTY INT,
	-- 度量
	MEASURE VARCHAR(255) COMMENT '度量',
	-- 图片
	PIC VARCHAR(255) COMMENT '图片',
	-- 入库数量
	IN_QTY INT COMMENT '入库数量',
	-- 出库数量
	OUT_QTY INT COMMENT '出库数量',
	-- 剩余数量
	LEFT_QTY INT COMMENT '剩余数量',
	PRIMARY KEY (ID)
);



/* Create Foreign Keys */

ALTER TABLE SPECIMEN_MATERIAL_REGISTER_ITEM
	ADD FOREIGN KEY (ABSTRACTS)
	REFERENCES ENTRUST_ABSTRACTS (ID)
	
	
;


ALTER TABLE ENTRUST_ABSTRACTS
	ADD FOREIGN KEY (REGISTER_ID)
	REFERENCES ENTRUST_REGISTER (ID)
	
	
;


ALTER TABLE SPECIMEN_MATERIAL_REGISTER
	ADD FOREIGN KEY (REGISTER_ID)
	REFERENCES ENTRUST_REGISTER (ID)
	
	
;


ALTER TABLE SPECIMEN_MATERIAL_IN_ITEM
	ADD FOREIGN KEY (MATERIAL_IN_ID)
	REFERENCES SPECIMEN_MATERIAL_IN (ID)
	
	
;


ALTER TABLE SPECIMEN_MATERIAL_OUT_ITEM
	ADD FOREIGN KEY (MATERIAL_OUT_ID)
	REFERENCES SPECIMEN_MATERIAL_OUT (ID)
	
	
;


ALTER TABLE SPECIMEN_MATERIAL_IN
	ADD FOREIGN KEY (MATERIAL_REGISTER_ID)
	REFERENCES SPECIMEN_MATERIAL_REGISTER (ID)
	
	
;


ALTER TABLE SPECIMEN_MATERIAL_OUT
	ADD FOREIGN KEY (MATERIAL_REGISTER_ID)
	REFERENCES SPECIMEN_MATERIAL_REGISTER (ID)
	
	
;


ALTER TABLE SPECIMEN_MATERIAL_REGISTER_ITEM
	ADD FOREIGN KEY (MATERIAL_REGISTER_ID)
	REFERENCES SPECIMEN_MATERIAL_REGISTER (ID)
	
	
;


