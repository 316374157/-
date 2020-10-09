package com.phj.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 31637
 * @date 2020/9/4 17:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailReturnVO implements Serializable {
    private static final long serialVersionUID = 5782778806679952641L;

    // 回报信息主键
    private Integer returnId;
    // 当前档位需支持的金额
    private Integer supportMoney;
    // 单笔限购，取值为 0 时无限额，取值为 1 时有限额
    private Integer signalPurchase;
    // 具体限额数量
    private Integer purchase;
    // 当前该档位支持者数量
    private Integer supproterCount = 0;
    //运费，取值为 0 时表示包邮
    private Integer freight;
    // 众筹成功后多少天发货
    private Integer returnDate;
    // 回报内容
    private String content;
}