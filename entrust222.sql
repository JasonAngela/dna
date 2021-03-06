SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS DNA_PI_RESULT_ITEM;
DROP TABLE IF EXISTS DNA_PI_RESULT;




/* Create Tables */

CREATE TABLE DNA_PI_RESULT
(
	ID VARCHAR(64) NOT NULL,
	PARENT_CODE VARCHAR(255),
	CHILD_CODE VARCHAR(255),
	REGISTER_ID VARCHAR(40),
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


CREATE TABLE DNA_PI_RESULT_ITEM
(
	ID VARCHAR(64) NOT NULL,
	-- 基因座
	GENE_LOCI VARCHAR(255) COMMENT '基因座',
	P_VALUE INT,
	Q_VALUE INT,
	P_PROB DECIMAL(10,4),
	Q_PROB DECIMAL(10,4),
	-- pi
	PI DECIMAL(10,4) COMMENT 'pi',
	RESULT_ID VARCHAR(64) NOT NULL,
	PRIMARY KEY (ID)
);



/* Create Foreign Keys */

ALTER TABLE DNA_PI_RESULT_ITEM
	ADD FOREIGN KEY (RESULT_ID)
	REFERENCES DNA_PI_RESULT (ID)
;



