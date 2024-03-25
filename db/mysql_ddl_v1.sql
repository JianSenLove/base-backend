CREATE TABLE mirageledger_user (
                                   ID_ varchar(64) PRIMARY KEY NOT NULL COMMENT 'ID',
                                   NAME_ varchar(255) NOT NULL COMMENT '用户名称',
                                   CODE_ varchar(64) NOT NULL COMMENT '登录账号',
                                   DESC_ varchar(255) NULL DEFAULT NULL COMMENT '用户描述',
                                   PASSWORD_ varchar(64) NOT NULL COMMENT '账号密码',
                                   CREATE_TIME_ timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                   UPDATE_TIME_ timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT = '用户信息表';

CREATE TABLE mirageledger_product (
                                      ID_ varchar(64) PRIMARY KEY NOT NULL COMMENT 'ID',
                                      NAME_ varchar(255) NOT NULL COMMENT '商品名称',
                                      CATEGORY_ID varchar(64) NOT NULL COMMENT '类别ID',
                                      PRICE_ double NOT NULL COMMENT '商品价格',
                                      STOCK_ int NOT NULL COMMENT '商品库存',
                                      USER_ID varchar(64) NOT NULL COMMENT '用户ID',
                                      DESC_ varchar(255) NULL DEFAULT NULL COMMENT '商品描述',
                                      CREATE_TIME_ timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                      UPDATE_TIME_ timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT = '商品信息表';

CREATE TABLE mirageledger_category (
                                       ID_ varchar(64) PRIMARY KEY NOT NULL COMMENT 'ID',
                                       NAME_ varchar(255) NOT NULL COMMENT '类别名称',
                                       DESC_ varchar(255) NULL DEFAULT NULL COMMENT '描述',
                                       CREATE_TIME_ timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                       UPDATE_TIME_ timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT = '商品类别表';

CREATE TABLE mirageledger_cart (
                                   ID_ varchar(64) PRIMARY KEY NOT NULL COMMENT 'ID',
                                   USER_ID varchar(64) NOT NULL COMMENT '用户ID',
                                   PRODUCT_ID varchar(64) NOT NULL COMMENT '商品ID',
                                   QUANTITY_ int NOT NULL COMMENT '数量',
                                   CREATE_TIME timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                   UPDATE_TIME timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT = '用户购物车表';

CREATE TABLE mirageledger_view_history (
                                           ID_ varchar(64) PRIMARY KEY NOT NULL COMMENT 'ID',
                                           USER_ID varchar(64) NOT NULL COMMENT '用户ID',
                                           PRODUCT_ID varchar(64) NOT NULL COMMENT '商品ID',
                                           COUNT_ int NOT NULL COMMENT '浏览次数',
                                           CREATE_TIME_ timestamp NOT NULL COMMENT '创建时间'
) COMMENT = '用户商品浏览记录表';

CREATE TABLE mirageledger_order (
                                    ID_ varchar(64) PRIMARY KEY NOT NULL COMMENT 'ID',
                                    USER_ID varchar(64) NOT NULL COMMENT '用户ID',
                                    ORDER_PRICE double NOT NULL COMMENT '订单总价',
                                    STATUS_ varchar(64) NOT NULL COMMENT '订单状态',
                                    CREATE_TIME timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                    UPDATE_TIME timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT = '订单表';

CREATE TABLE mirageledger_order_product (
                                            ID_ varchar(64) PRIMARY KEY NOT NULL COMMENT 'ID',
                                            ORDER_ID varchar(64) NOT NULL COMMENT '订单ID',
                                            PRODUCT_ID varchar(64) NOT NULL COMMENT '商品ID',
                                            QUANTITY_ int NOT NULL COMMENT '数量',
                                            PRICE_ double NOT NULL COMMENT '商品单价',
                                            TOTAL_PRICE double NOT NULL COMMENT '商品总价',
                                            CREATE_TIME timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                            UPDATE_TIME timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT = '订单商品关联表';
