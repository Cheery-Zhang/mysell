<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <#--   边栏sidebar-->
<#--    <#include "../common/nav.ftl">-->
    <#include "../common/com.ftl">
    <#--    主要内容-->
    <div id="page-content-wrapper">
         <div class="container">
           <div class="row clearfix">
        <div class="col-md-10 column">
            <table class="table">
                <thead>
                <tr>
                    <th>类目id</th>
                    <th>名字</th>
                    <th>type</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#list productCategoryList as productCategory>
                    <tr>
                        <td>${productCategory.categoryId}</td>
                        <td>${productCategory.categoryName}</td>
                        <td>${productCategory.categoryType}</td>
                        <td>${productCategory.createTime}</td>
                        <td>${productCategory.updateTime}</td>
                        <td>
                            <a href="/mysell/seller/category/index?categoryId=${productCategory.categoryId}">修改</a>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
         </div>
    </div>
</div>

</body>
</html>  