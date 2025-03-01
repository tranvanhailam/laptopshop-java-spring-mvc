<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>

        <!-- Reset CSS -->
        <link rel="stylesheet" href="/css/reset.css" />

        <!-- Font Roboto -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
            rel="stylesheet"
        />

        <!-- Style CSS  -->
        <link rel="stylesheet" href="/css/admin/user/table-user.css" />
    </head>
    <body>
        <main>
            <section class="main">
                <div class="container">
                    <div class="row">
                        <h1 class="row__header">Table users</h1>
                        <a
                            href="/admin/user/create"
                            class="row__btn-create-user"
                        >
                            Create a user
                        </a>
                    </div>
                    <div class="line"></div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Email</th>
                                <th>Full Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${userList}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.email}</td>
                                    <td>${user.fullName}</td>
                                    <td>
                                        <div class="table__group-btn">
                                            <a
                                                class="table__btn-view"
                                                href="/admin/user/${user.id}"
                                            >
                                                View
                                            </a>
                                            <a
                                                class="table__btn-update"
                                                href="/admin/user/update/${user.id}"
                                            >
                                                Update
                                            </a>
                                            <a
                                                class="table__btn-delete"
                                                href="/admin/user/delete/${user.id}"
                                            >
                                                Delete
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    </body>
</html>
