<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" tppabs="http://v3.bootcss.com/examples/dashboard/dashboard.css" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
<#--            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">-->
<#--                <span class="sr-only">Toggle navigation</span>-->
<#--                <span class="icon-bar"></span>-->
<#--                <span class="icon-bar"></span>-->
<#--                <span class="icon-bar"></span>-->
<#--            </button>-->
            <a class="navbar-brand" href="#">卖家管理系统</a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">Overview <span class="sr-only">订单</span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>

                    <ul class="dropdown-menu" role="menu">
                        <li class="dropdown-header">操作</li>
                        <li><a href="/mysell/seller/product/list">列表</a></li>
                        <li><a href="/mysell/seller/product/index">新增</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li class="dropdown-header">操作</li>
                        <li><a href="/mysell/seller/category/list">列表</a></li>
                        <li><a href="/mysell/seller/category/index">新增</a></li>
                    </ul>
                </li>
                <li>
                    <a href="/mysell/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="table-responsive">
                <table class="table table-striped">
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
            <div class="col-md-12 column">
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

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../../../cdn.bootcss.com/jquery/1.11.3/jquery.min.js" tppabs="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../../../cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js" tppabs="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="../../assets/js/vendor/holder.min.js" tppabs="http://v3.bootcss.com/assets/js/vendor/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js" tppabs="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

</body>
</html>  