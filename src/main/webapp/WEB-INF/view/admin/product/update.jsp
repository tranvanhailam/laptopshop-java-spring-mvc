<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
        />
        <title>Update Product - Laptopshop</title>
        <link href="/css/styles.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <script>
            $(document).ready(() => {
                //Trang web đã load
                const productFile = $("#productFile"); //Lấy elm có id productFile

                const orgImage = "${product.image}";
                if (orgImage) {
                    const urlImage = "/images/product/" + orgImage;
                    $("#productPreview").attr("src", urlImage);
                    $("#productPreview").css({ display: "block" });
                }
                productFile.change(function (e) {
                    const imgURL = URL.createObjectURL(e.target.files[0]); //Link URL hiển thị ảnh
                    $("#productPreview").attr("src", imgURL);
                    $("#productPreview").css({ display: "block" });
                });
            });
        </script>

        <script
            src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
            crossorigin="anonymous"
        ></script>
    </head>

    <body class="sb-nav-fixed">
        <jsp:include page="../layout/header.jsp" />
        <div id="layoutSidenav">
            <jsp:include page="../layout/sidebar.jsp" />
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Manage Products</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item">
                                <a href="/admin">Dashboard</a>
                            </li>
                            <li class="breadcrumb-item active">Product</li>
                        </ol>
                        <div class="mt-5">
                            <div class="row">
                                <div class="col-md-6 col-12 mx-auto">
                                    <h3>Update a product</h3>
                                    <hr />
                                    <form:form
                                        method="post"
                                        action="/admin/product/update"
                                        modelAttribute="product"
                                        class="row"
                                        enctype="multipart/form-data"
                                    >
                                        <div class="mb-3" style="display: none">
                                            <label class="form-label"
                                                >Id:</label
                                            >
                                            <form:input
                                                type="text"
                                                class="form-control"
                                                path="id"
                                            />
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <c:set var="errorName">
                                                <form:errors
                                                    path="name"
                                                    class="invalid-feedback"
                                                />
                                            </c:set>
                                            <label class="form-label"
                                                >Name:</label
                                            >
                                            <form:input
                                                type="text"
                                                class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                                                path="name"
                                            />
                                            ${errorName}
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <c:set var="errorPrice">
                                                <form:errors
                                                    path="price"
                                                    class="invalid-feedback"
                                                />
                                            </c:set>
                                            <label class="form-label"
                                                >Price:</label
                                            >
                                            <form:input
                                                type="number"
                                                class="form-control ${not empty errorPrice ? 'is-invalid' : ''}"
                                                path="price"
                                            />
                                            ${errorPrice}
                                        </div>
                                        <div class="mb-3 col-12">
                                            <c:set var="errorDetailDesc">
                                                <form:errors
                                                    path="detailDesc"
                                                    class="invalid-feedback"
                                                />
                                            </c:set>
                                            <label class="form-label"
                                                >Detail description:</label
                                            >
                                            <form:textarea
                                                type="text"
                                                class="form-control ${not empty errorDetailDesc ? 'is-invalid' : ''}"
                                                path="detailDesc"
                                            />
                                            ${errorDetailDesc}
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <c:set var="errorShortDesc">
                                                <form:errors
                                                    path="shortDesc"
                                                    class="invalid-feedback"
                                                />
                                            </c:set>
                                            <label class="form-label"
                                                >Short description:</label
                                            >
                                            <form:input
                                                type="text"
                                                class="form-control ${not empty errorShortDesc ? 'is-invalid' : ''}"
                                                path="shortDesc"
                                            />
                                            ${errorShortDesc}
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <c:set var="errorQuantity">
                                                <form:errors
                                                    path="quantity"
                                                    class="invalid-feedback"
                                                />
                                            </c:set>
                                            <label class="form-label"
                                                >Quantity:</label
                                            >
                                            <form:input
                                                type="number"
                                                class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}"
                                                path="quantity"
                                            />
                                            ${errorQuantity}
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label"
                                                >Factory:</label
                                            >
                                            <form:select
                                                class="form-select"
                                                path="factory"
                                            >
                                                <form:option value="Apple">
                                                    Apple
                                                </form:option>
                                                <form:option value="Asus">
                                                    Asus
                                                </form:option>
                                                <form:option value="Lenovo">
                                                    Lenovo
                                                </form:option>
                                                <form:option value="Dell">
                                                    Dell
                                                </form:option>
                                                <form:option value="LG">
                                                    LG
                                                </form:option>
                                                <form:option value="Acer">
                                                    Acer
                                                </form:option>
                                            </form:select>
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label"
                                                >Target:</label
                                            >
                                            <form:select
                                                class="form-select"
                                                path="target"
                                            >
                                                <form:option value="Gaming">
                                                    Gaming
                                                </form:option>
                                                <form:option
                                                    value="Sinh viên - Văn phòng"
                                                >
                                                    Sinh viên - Văn phòng
                                                </form:option>
                                                <form:option
                                                    value="Thiết kế đồ họa"
                                                >
                                                    Thiết kế đồ họa
                                                </form:option>
                                                <form:option value="Mỏng nhẹ">
                                                    Mỏng nhẹ
                                                </form:option>
                                                <form:option value="Doanh nhân">
                                                    Doanh nhân
                                                </form:option>
                                                <form:option
                                                    value="Công nghệ thông tin"
                                                >
                                                    Công nghệ thông tin
                                                </form:option>
                                            </form:select>
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <label
                                                for="productFile"
                                                class="form-label"
                                            >
                                                Image:
                                            </label>
                                            <input
                                                class="form-control"
                                                type="file"
                                                id="productFile"
                                                accept=".png, .jpg, .jpeg"
                                                name="productFile"
                                            />
                                        </div>
                                        <div class="col-12 mb-3">
                                            <img
                                                style="max-height: 250px"
                                                alt="product preview"
                                                id="productPreview"
                                            />
                                        </div>
                                        <div class="col-12 mb-5">
                                            <button
                                                type="submit"
                                                class="btn btn-primary"
                                            >
                                                Update
                                            </button>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <jsp:include page="../layout/footer.jsp" />
            </div>
        </div>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"
        ></script>
        <script src="/js/scripts.js"></script>
    </body>
</html>
