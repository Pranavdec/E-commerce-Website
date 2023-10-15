<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header_shop.jsp"%>

<div class="content">
        <div class="container mt-5">
                <div class="row">
                        <div class="col">
                                <div class="content">
                                        <h2 class="mb-4">Items List</h2>
                                        <div class="table-responsive">
                                                ${items_table}
                                        </div>
                                </div>
                        </div>
                </div>
        </div>
</div>

<%@ include file="footer.jsp"%>
