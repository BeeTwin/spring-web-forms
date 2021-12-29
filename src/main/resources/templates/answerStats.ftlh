<#import "parts/common.ftl" as c>
<@c.page>
    <div><h1>Ответы на форму ${form.name} от ${form.author.username}</h1></div>
    <table>
        <thead>
        <tr>
            <th>Имя</th>
            <th>Вопрос</th>
            <th>Ответ</th>
            <th>Время</th>
        </tr>
        </thead>
        <tbody>
        <#list answers as answer>
            <tr id="row">
                <td>
                    ${answer.author}
                </td>
                <td>
                    ${answer.question.question}
                </td>
                <td>
                    ${answer.answer}
                </td>
                <td>
                    ${answer.date}
                </td>
            </tr>
        <#else>
            Никто еще не ответил на эту форму :(
        </#list>
        </tbody>
    </table>
</@c.page>