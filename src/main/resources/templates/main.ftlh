<#import "parts/common.ftlh" as c>

<@c.page>
    <header>
        <div class="d-flex flex-column flex-md-row align-items-center pb-3 mb-4 border-bottom">
            <a href="/" class="d-flex align-items-center text-dark text-decoration-none">
            </a>
            <nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto">
                <a class="btn btn-primary" href="/add">Добавить новость</a>
            </nav>
        </div>
    </header>

    <h1>Лента новостей:</h1>
    <div>
       <#include "parts/newsList.ftlh"/>
    </div>
</@c.page>