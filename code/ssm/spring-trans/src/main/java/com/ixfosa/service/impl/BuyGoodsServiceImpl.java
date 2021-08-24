package com.ixfosa.service.impl;

import com.ixfosa.dao.GoodsDao;
import com.ixfosa.dao.SaleDao;
import com.ixfosa.ex.NotEnoughException;
import com.ixfosa.pojo.Goods;
import com.ixfosa.pojo.Sale;
import com.ixfosa.service.BuyGoodsService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by ixfosa on 2021/4/23 18:09
 */
@Repository
public class BuyGoodsServiceImpl implements BuyGoodsService {

    @Resource
    private GoodsDao goodsDao;
    @Resource
    private SaleDao saleDao;

    @Override
    public void buy(Integer goodsId, Integer nums) {
        // 记录销售信息，向sale表添加记录
        Sale sale = new Sale();
        sale.setGid(goodsId);
        sale.setNums(nums);
        saleDao.insertSale(sale);

        // 更新库存
        Goods goods = goodsDao.selectGoods(goodsId);
        if (goods == null) {
            // 商品不存在
            throw new NullPointerException("编号是："+goodsId+",商品不存在");
        }
        if (goods.getAmount() < nums) {
            // 商品库存不足
            throw new NotEnoughException("编号是："+ goodsId + ",商品库存不足");
        }

        // 修改库存
        Goods buyGoods = new Goods();
        buyGoods.setId(goodsId);
        buyGoods.setAmount(nums);
        goodsDao.updateGoods(buyGoods);
    }
}
