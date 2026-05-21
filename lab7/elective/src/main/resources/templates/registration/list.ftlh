<#include "../_header.ftlh">

<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <h1 class="page-title">Записи на курси</h1>
        <p class="text-muted mb-0 small">Активні реєстрації студентів</p>
    </div>
    <a href="/registrations/new" class="btn btn-primary">
        <i class="bi bi-clipboard-plus me-1"></i>Новий запис
    </a>
</div>

<div class="card table-card">
    <table class="table mb-0">
        <thead>
            <tr><th>#</th><th>Студент</th><th>Курс</th><th>Викладач</th><th>Дата</th><th></th></tr>
        </thead>
        <tbody>
            <#if registrations?has_content>
                <#list registrations as r>
                <tr>
                    <td class="text-muted small">${r.id}</td>
                    <td><a href="/students/${r.student.id}">${r.student.fullName}</a></td>
                    <td><a href="/courses/${r.course.id}">${r.course.name}</a></td>
                    <td class="text-muted">${(r.course.teacher.fullName)!"—"}</td>
                    <td class="text-muted small">${r.registrationDate}</td>
                    <td class="text-end">
                        <form method="post" action="/registrations/${r.id}/delete" class="d-inline"
                              onsubmit="return confirm('Скасувати цей запис?')">
                            <button class="btn btn-sm btn-outline-danger">
                                <i class="bi bi-x-lg me-1"></i>Скасувати
                            </button>
                        </form>
                    </td>
                </tr>
                </#list>
            <#else>
                <tr><td colspan="6">
                    <div class="empty-state text-center">
                        <i class="bi bi-clipboard fs-1 d-block mb-2"></i>Записів поки немає
                    </div>
                </td></tr>
            </#if>
        </tbody>
    </table>
</div>

<#include "../_footer.ftlh">
