<#import "pager.ftlh" as p>

<@p.pager url page/>
<#list page.content as news>
    <div class="alert alert-info my-3">
        <table>
            <tbody>
            <tr>
                <td>
                    <h3>${news.newsTittle}</h3>
                    <p>${news.newsText}</p>
                    <a>${news.dateAdded}</a>
                </td>
                <td>
                    <div>
                        <#if news.filename??>
                            <img src="/img/${news.filename}" width="100" height="100">
                        </#if>
                    </div>
                </td>
            </tr>
            <br/>
            </tbody>
        </table>
    </div>
<#else>
    Нет новостей
</#list>
<@p.pager url page/>