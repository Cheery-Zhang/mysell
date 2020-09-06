<!DOCTYPE html>
<html lang="zh-CN">

<#--    <meta http-equiv="X-UA-Compatible" content="IE=edge">-->
<#--    <meta name="viewport" content="width=device-width, initial-scale=1">-->
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- Bootstrap -->
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--    <#include "../common/nav.ftl">-->
        <#include "../common/com.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
               <div class="col-md-10 column">
                <div class="row">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <#--                    <th>创建时间</th>-->
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().message}</td>
                                <td>${orderDTO.getPayStatusEnum().message}</td>
                                <#--                   <td> ${orderDTO.createTime}</td>-->
                                <td>
                                    <a href="/mysell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                                </td>

                                <td>
                                    <#if orderDTO.getOrderStatusEnum().message=="新订单">
                                        <a href="/mysell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                    </#if>

                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <div class="row">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/mysell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..<orderDTOPage.getTotalPages()+1 as index>
                            <#if currentPage==index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/mysell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/mysell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<script>
    var websocket = null;
    if('websocket' in window){
        websocket = new WebSocket('ws://127.0.0.1/mysell/webSocket');
    }else {
        alert('该浏览器不支持websoket！');
    }
    websocket.onopen = function (event) {
        console.log('建立连接');
    }
    websocket.onclose = function (event) {
        console.log('连接关闭');
    }
    websocket.onmessage = function (event) {
        console.log('收到消息：'+event.data);
        //弹窗提醒
    }
    websocket.onerror = function (event) {
        console.log('websocker通信发生错误');
    }
    window.onbeforeunload = function () {
        websocket.close();
    }
</script>
</body>
</html>

