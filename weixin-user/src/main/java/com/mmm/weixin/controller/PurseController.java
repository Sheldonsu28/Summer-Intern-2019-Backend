package com.mmm.weixin.controller;

import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.InterUserTransactionDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.service.impl.PurseServiceImp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api("钱包")
@RestController
public class PurseController extends BaseController {

    @Autowired
    private PurseServiceImp purseService;


    @ApiOperation("负责用户之间的转账交易")
    @PostMapping("/InterUserTransaction")
    public Result InterUserTransaction(@RequestBody InterUserTransactionDto transactionInfo){
        purseService.InterUserTransaction(transactionInfo);
        return success();
    }

    @ApiOperation("查询用户钱包内的余额")
    @PostMapping("/getUserBalance")
    public Result getUserBalance(HttpServletRequest request){
        return success(purseService.getPurseBalance(request));
    }

}
