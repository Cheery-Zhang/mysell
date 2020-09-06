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
                        <form role="form" method="post" action="/mysell/seller/product/save" >
                            <div class="form-group">
                                <label>名称</label>
                                <input name="productName" class="form-control" type="text" value="${(productInfo.productName)!''}"/>
                            </div>
                            <div class="form-group">
                                <label>价格</label>
                                <input name="productPrice" class="form-control" type="text" value="${(productInfo.productPrice)!''}"/>
                            </div>
                            <div class="form-group">
                                <label>库存</label>
                                <input name="productStock" class="form-control" type="number" value="${(productInfo.productStock)!''}"/>
                            </div>
                            <div class="form-group">
                                <label>描述</label>
                                <input name="productDescription" class="form-control" type="text" value="${(productInfo.productDescription)!''}"/>
                            </div>
                            <div class="form-group">
                                <label>图片</label>
                                <img height="100" width="100" scr="${(productInfo.productIcon)!''}" alt="">
                                <input name="productIcon" class="form-control" type="text" value="${(productInfo.productIcon)!''}"/>
                            </div>
                            <div class="form-group">
                                <label>类目</label>
                                <select name="categoryType" class="form-control">
                                    <#list productCategoryList as productCategory>
                                        <option value="${productCategory.categoryType}"
                                            <#if (productInfo.categoryType)??&&productInfo.categoryType==productCategory.categoryType>
                                                selected
                                            </#if>
                                            >${productCategory.categoryName}
                                        </option>
                                    </#list>
                                </select>
                            </div>
                            <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
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