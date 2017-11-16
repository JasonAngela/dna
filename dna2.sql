SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS DNA_EXPERIMENT_BOARD;
DROP TABLE IF EXISTS DNA_EXPERIMENT_IMPORT;
DROP TABLE IF EXISTS DNA_EXPERIMENT_SPECIMEN;
DROP TABLE IF EXISTS DNA_EXPERIMENT_STR;
DROP TABLE IF EXISTS DNA_EXPERIMENT;




/* Create Tables */

CREATE TABLE DNA_EXPERIMENT
(
	ID VARCHAR(64) NOT NULL,
	-- 编码
	CODE VARCHAR(255) COMMENT '编码',
	-- 基因盒
	CASSETTE_ID VARCHAR(64) COMMENT '基因盒',
	LAB_ID VARCHAR(64),
	START_TIME DATETIME,
	END_TIME DATETIME,
	STATUS INT,
	PROC_INS_ID VARCHAR(64),
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


CREATE TABLE DNA_EXPERIMENT_BOARD
(
	ID VARCHAR(64) NOT NULL,
	BOARD_CODE VARCHAR(255),
	EXPERIMENT_ID VARCHAR(64) NOT NULL,
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


CREATE TABLE DNA_EXPERIMENT_IMPORT
(
	ID VARCHAR(64) NOT NULL,
	EXPERIMENT_ID VARCHAR(64) NOT NULL,
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


CREATE TABLE DNA_EXPERIMENT_SPECIMEN
(
	ID VARCHAR(64) NOT NULL,
	EXPERIMENT_ID VARCHAR(64) NOT NULL,
	-- 检样编码
	SPECIMEN_CODE VARCHAR(255) COMMENT '检样编码',
	BOARD_ID VARCHAR(64),
	HANG INT,
	LIE INT,
	-- 基因值
	GENE_VALUE VARCHAR(255) COMMENT '基因值',
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


CREATE TABLE DNA_EXPERIMENT_STR
(
	ID VARCHAR(64) NOT NULL,
	EXPERIMENT_ID VARCHAR(64) NOT NULL,
	-- 检样编码
	SPECIMEN_CODE VARCHAR(255) COMMENT '检样编码',
	GENE_LOCI VARCHAR(255),
	X VARCHAR(255),
	Y VARCHAR(255),
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



/* Create Foreign Keys */

ALTER TABLE DNA_EXPERIMENT_BOARD
	ADD FOREIGN KEY (EXPERIMENT_ID)
	REFERENCES DNA_EXPERIMENT (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE DNA_EXPERIMENT_IMPORT
	ADD FOREIGN KEY (EXPERIMENT_ID)
	REFERENCES DNA_EXPERIMENT (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE DNA_EXPERIMENT_SPECIMEN
	ADD FOREIGN KEY (EXPERIMENT_ID)
	REFERENCES DNA_EXPERIMENT (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE DNA_EXPERIMENT_STR
	ADD FOREIGN KEY (EXPERIMENT_ID)
	REFERENCES DNA_EXPERIMENT (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


