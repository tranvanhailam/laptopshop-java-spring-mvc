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
        <link rel="stylesheet" href="/css/admin/user/user-detail.css" />
    </head>
    <body>
        <main>
            <section class="main">
                <div class="container">
                    <h1 class="header">User detail</h1>
                    <div class="line"></div>
                    <div class="card">
                        <p class="card__header">User information</p>
                        <ul class="info-list">
                            <li class="info-item">ID: ${user.id}</li>
                            <li class="info-item">Email: ${user.email}</li>
                            <li class="info-item">
                                Password: ${user.password}
                            </li>
                            <li class="info-item">
                                Full name: ${user.fullName}
                            </li>
                            <li class="info-item">Address: ${user.address}</li>
                            <li class="info-item">
                                Phone number: ${user.phoneNumber}
                            </li>
                        </ul>
                    </div>
                    <a class="btn-back" href="/admin/user">Back</a>
                </div>
            </section>
        </main>
    </body>
</html>
