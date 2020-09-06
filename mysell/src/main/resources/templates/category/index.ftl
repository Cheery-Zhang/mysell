<!DOCTYPE html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--   边栏sidebar-->
<#--    <#include "../common/nav.ftl">-->
    <#include "../common/com.ftl">
       <#--    主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-10 column">
                        <form role="form" method="post" action="/mysell/seller/category/save" >
                            <div class="form-group">
                                <label>名字</label>
                                <input name="categoryName" class="form-control" type="text" value="${(productCategory.categoryName)!''}"/>
                            </div>
                            <div class="form-group">
                                <label>type</label>
                                <input name="categoryType" class="form-control" type="number" value="${(productCategory.categoryType)!''}"/>
                            </div>
                            <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}">
                            <button class="btn btn-default" type="submit">提交</button>
                        </form>
                    </div>
                </div>
        </div>
    </div>

</div>

<#--<#list orderDTOPage.content as orderDTO>-->
<#--    ${orderDTO.orderId}<br>-->
<#--</#list>-->
</body>
</html>  