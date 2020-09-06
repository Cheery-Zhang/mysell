<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#--   边栏sidebar-->
<#--    <#include "../common/nav.ftl">-->
    <#include "../common/com.ftl">
    <div id="page-content-wrapper">
   <div class="container">
      <div class="row clearfix">
        <div class="col-md-4 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>订单id</th>
                    <th>订单总金额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${orderDTO.orderId}</td>
                    <td>${orderDTO.orderAmount}</td>
                </tr>
                </tbody>
            </table>
        </div>
<#--        <订单详情表数据>-->
        <div class="col-md-9 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>商品id</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>总额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <#list orderDTO.orderDetailList as orderDetail>
                    <td>${orderDetail.productId}</td>
                    <td>${orderDetail.productName}</td>
                    <td>${orderDetail.productPrice}</td>
                    <td>${orderDetail.productQuantity}</td>
                    <td>${orderDetail.productPrice*orderDetail.productQuantity}</td>
                    </#list>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="col-md-1 2 column">
            <#if orderDTO.getOrderStatusEnum().message=="新订单">
            <a class="btn btn-default btn-primary" type="button" href="/mysell/seller/order/finish?orderId=${orderDTO.orderId}">完结订单</a>
            <a class="btn btn-default btn-danger" type="button" href="/mysell/seller/order/cancel?orderId=${orderDTO.orderId}">取消订单</a>
            </#if>
        </div>
          </div>

    </div>
    </div>
</div>

</body>
</html>  