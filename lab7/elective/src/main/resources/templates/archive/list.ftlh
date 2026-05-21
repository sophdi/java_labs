<#include "../_header.ftlh">

<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <h1 class="page-title">Архів оцінок</h1>
        <p class="text-muted mb-0 small">Завершені курси та виставлені оцінки</p>
    </div>
    <a href="/archive/grade" class="btn btn-primary">
        <i class="bi bi-award me-1"></i>Виставити оцінку
    </a>
</div>

<div class="card table-card">
    <table class="table mb-0">
        <thead>
            <tr><th>#</th><th>Студент</th><th>Курс</th><th>Оцінка</th><th>Результат</th><th>Дата</th><th></th></tr>
        </thead>
        <tbody>
            <#if archives?has_content>
                <#list archives as a>
                <tr>
                    <td class="text-muted small">${a.id}</td>
                    <td><a href="/students/${a.registration.student.id}">${a.registration.student.fullName}</a></td>
                    <td><a href="/courses/${a.registration.course.id}">${a.registration.course.name}</a></td>
                    <td class="fw-bold">${a.grade}<span class="text-muted fw-normal">/100</span></td>
                    <td>
                        <#if a.grade gte 90>
                            <span class="badge bg-success">Відмінно (5)</span>
                        <#elseif a.grade gte 75>
                            <span class="badge bg-primary">Добре (4)</span>
                        <#elseif a.grade gte 60>
                            <span class="badge bg-warning text-dark">Задовільно (3)</span>
                        <#else>
                            <span class="badge bg-danger">Незадовільно (2)</span>
                        </#if>
                    </td>
                    <td class="text-muted small">${a.completionDate}</td>
                    <td class="text-end">
                        <form method="post" action="/archive/${a.id}/delete" class="d-inline"
                              onsubmit="return confirm('Видалити оцінку?')">
                            <button class="btn btn-sm btn-outline-danger btn-icon">
                                <i class="bi bi-trash"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                </#list>
            <#else>
                <tr><td colspan="7">
                    <div class="empty-state text-center">
                        <i class="bi bi-archive fs-1 d-block mb-2"></i>Архів порожній
                    </div>
                </td></tr>
            </#if>
        </tbody>
    </table>
</div>

<#include "../_footer.ftlh">
