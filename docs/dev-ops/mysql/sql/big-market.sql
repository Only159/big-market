SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE database if NOT EXISTS `big_market` default character set utf8mb4 collate utf8mb4_0900_ai_ci;
use `big_market`;
-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy`
(
    `id`            bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `strategy_id`   bigint(8)           NOT NULL COMMENT '抽奖策略ID',
    `strategy_desc` varchar(128)        NOT NULL COMMENT '抽奖策略描述',
    `rule_models`   varchar(255)                 DEFAULT NULL COMMENT '策略模型',
    `create_time`   datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_strategy_id` (`strategy_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- ----------------------------
-- Records of strategy
-- ----------------------------
INSERT INTO `strategy` (`id`, `strategy_id`, `rule_models`, `strategy_desc`)
VALUES (1, 100001, 'rule_weight,rule_blacklist', '抽奖策略');
COMMIT;

-- ----------------------------
-- Table structure for strategy_award
-- ----------------------------
DROP TABLE IF EXISTS `strategy_award`;
CREATE TABLE `strategy_award`
(
    `id`                  bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `strategy_id`         bigint(8)           NOT NULL COMMENT '抽奖策略ID',
    `award_id`            int(8)              NOT NULL COMMENT '抽奖奖品ID - 内部流转使用',
    `award_title`         varchar(128)        NOT NULL COMMENT '抽奖奖品标题',
    `award_subtitle`      varchar(128)                 DEFAULT NULL COMMENT '抽奖奖品副标题',
    `award_count`         int(8)              NOT NULL DEFAULT '0' COMMENT '奖品库存总量',
    `award_count_surplus` int(8)              NOT NULL DEFAULT '0' COMMENT '奖品库存剩余',
    `award_rate`          decimal(6, 4)       NOT NULL COMMENT '奖品中奖概率',
    `rule_models`         varchar(256)                 DEFAULT NULL COMMENT '规则模型，rule配置的模型同步到此表，便于使用',
    `sort`                int(2)              NOT NULL DEFAULT '0' COMMENT '排序',
    `create_time`         datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_strategy_id_award_id` (`strategy_id`, `award_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- ----------------------------
-- Records of strategy_award
-- ----------------------------
INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`,
                              `award_count_surplus`, `award_rate`, `rule_models`, `sort`)
VALUES (1, 100001, 101, '随机积分', NULL, 80000, 80000, 80.0000, 'rule_random,rule_luck_award', 1),
       (2, 100001, 102, '5次使用', NULL, 10000, 10000, 10.0000, 'rule_luck_award', 2),
       (3, 100001, 103, '10次使用', NULL, 5000, 5000, 5.0000, 'rule_luck_award', 3),
       (4, 100001, 104, '20次使用', NULL, 4000, 4000, 4.0000, 'rule_luck_award', 4),
       (5, 100001, 105, '增加gpt-4对话模型', NULL, 600, 600, 0.6000, 'rule_luck_award', 5),
       (6, 100001, 106, '增加dall-e-2画图模型', NULL, 200, 200, 0.2000, 'rule_luck_award', 6),
       (7, 100001, 107, '增加dall-e-3画图模型', '抽奖1次后解锁', 200, 200, 0.2000, 'rule_lock,rule_luck_award', 7),
       (8, 100001, 108, '增加100次使用', '抽奖2次后解锁', 199, 199, 0.1999, 'rule_lock,rule_luck_award', 8),
       (9, 100001, 109, '解锁全部模型', '抽奖6次后解锁', 1, 1, 0.0001, 'rule_lock,rule_luck_award', 9);
COMMIT;
-- ----------------------------
-- Table structure for strategy_rule
-- ----------------------------
DROP TABLE IF EXISTS `strategy_rule`;
CREATE TABLE `strategy_rule`
(
    `id`          bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `strategy_id` int(8)              NOT NULL COMMENT '抽奖策略ID',
    `award_id`    int(8)                       DEFAULT NULL COMMENT '抽奖奖品ID【规则类型为策略，则不需要奖品ID】',
    `rule_type`   tinyint(1)          NOT NULL DEFAULT '0' COMMENT '抽象规则类型；1-策略规则、2-奖品规则',
    `rule_model`  varchar(16)         NOT NULL COMMENT '抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】',
    `rule_value`  varchar(64)         NOT NULL COMMENT '抽奖规则比值',
    `rule_desc`   varchar(128)        NOT NULL COMMENT '抽奖规则描述',
    `create_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_strategy_id_award_id` (`strategy_id`, `award_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- ----------------------------
-- Records of strategy_rule
-- ----------------------------
INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`)
VALUES (1, 100001, 101, 2, 'rule_random', '1,1000', '随机积分策略'),
       (2, 100001, 107, 2, 'rule_lock', '1', '抽奖1次后解锁'),
       (3, 100001, 108, 2, 'rule_lock', '2', '抽奖2次后解锁'),
       (4, 100001, 109, 2, 'rule_lock', '6', '抽奖6次后解锁'),
       (5, 100001, 107, 2, 'rule_luck_award', '1,100', '兜底奖品100以内随机积分'),
       (6, 100001, 108, 2, 'rule_luck_award', '1,100', '兜底奖品100以内随机积分'),
       (7, 100001, 101, 2, 'rule_luck_award', '1,10', '兜底奖品10以内随机积分'),
       (8, 100001, 102, 2, 'rule_luck_award', '1,20', '兜底奖品20以内随机积分'),
       (9, 100001, 103, 2, 'rule_luck_award', '1,30', '兜底奖品30以内随机积分'),
       (10, 100001, 104, 2, 'rule_luck_award', '1,40', '兜底奖品40以内随机积分'),
       (11, 100001, 105, 2, 'rule_luck_award', '1,50', '兜底奖品50以内随机积分'),
       (12, 100001, 106, 2, 'rule_luck_award', '1,60', '兜底奖品60以内随机积分'),
       (13, 100001, NULL, 1, 'rule_weight', '6000,102,103,104,105,106,107,108,109', '消耗6000分，必中奖范围'),
       (14, 100001, NULL, 1, 'rule_blacklist', '1', '黑名单抽奖，积分兜底');
COMMIT;
