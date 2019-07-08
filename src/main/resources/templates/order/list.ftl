<html>
<#include "../common/header.ftl">
    <meta charset="utf-8">
    <title>订单列表</title>
    <head></head>
    <!-- jQuery -->
    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <body>

    <div id="wrapper" class="toggled">

        <#--边栏sidebar-->
        <#include "../common/nav.ftl">


    <div id="page-content-wrapper">
    <div class="container-fluid">
        <table class="table table-bordered">
            <tr>
                <th>订单号</th>
                <th>姓名</th>
                <th>手机号</th>
                <th>地址</th>
                <th>金额</th>
                <th>订单状态</th>
                <th>支付状态</th>
                <th>创建时间</th>
                <th colspan="2">操作</th>
            </tr>
            <#list orderdtoPage.content as orderdto>
            <tr>
                <td>${orderdto.orderId}</td>
                <td>${orderdto.buyerName}</td>
                <td>${orderdto.buyerPhone}</td>
                <td>${orderdto.buyerAddress}</td>
                <td>${orderdto.orderAmount}</td>
                <td>${orderdto.getOrderStatusEnum().getMessage()}</td>
                <td>${orderdto.getPayStatusEnum().getMessage()}</td>
                <td>${orderdto.createTime}</td>
                <td> <a href="/sell/seller/order/details?orderId=${orderdto.orderId}">详情</a> </td>
                <td>
                    <#if orderdto.getOrderStatusEnum().getMessage()=="新订单">
                        <a href="/sell/seller/order/cancel?orderId=${orderdto.orderId}">取消</a>
                    </#if>

                </td>

            </tr>
            </#list>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination pull-right">
                <#if currentpage lte 1>
                    <li class="disabled">
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <#else>
                        <li>
                            <a href="/sell/seller/order/list?page=${currentpage - 1}&size=10">&laquo;</a>
                        </li>
                </#if>

                <#list  1..orderdtoPage.getTotalPages() as index >
                    <#if index==currentpage>
                        <li class="disabled"><a href="">${index}</a></li>
                        <#else>
                        <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                    </#if>

                </#list>
                <#if currentpage gte orderdtoPage.getTotalPages()>
                    <li class="disabled">
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                <#else>
                    <li>
                        <a href="/sell/seller/order/list?page=${currentpage + 1}&size=10">&laquo;</a>
                    </li>
                </#if>
            </ul>
        </nav>
    </div>
    </div>
    </div>

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>

</html>