<#include "../_header.ftlh">

<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <h1 class="page-title">Курси</h1>
        <p class="text-muted mb-0 small">Каталог факультативних курсів</p>
    </div>
    <a href="/courses/new" class="btn btn-primary">
        <i class="bi bi-plus-lg me-1"></i>Додати курс
    </a>
</div>

<div class="card table-card">
    <table class="table mb-0">
        <thead>
            <tr><th>#</th><th>Назва курсу</th><th>Викладач</th><th>Тижнів</th><th></th></tr>
        </thead>
        <tbody>
            <#if courses?has_content>
                <#list courses as c>
                <tr>
                    <td class="text-muted small">${c.id}</td>
                    <td>
                        <a href="/courses/${c.id}" class="fw-500">${c.name}</a>
                        <#if c.description?? && c.description?has_content>
                            <div class="text-muted small">${c.description?truncate(60, "…")}</div>
                        </#if>
                    </td>
                    <td class="text-muted">${(c.teacher.fullName)!"—"}</td>
                    <td><span class="badge bg-info bg-opacity-10 text-info">${c.durationWeeks} тиж.</span></td>
                    <td class="text-end">
                        <div class="d-flex gap-1 justify-content-end">
                            <a href="/courses/${c.id}" class="btn btn-sm btn-outline-secondary btn-icon" title="Переглянути">
                                <i class="bi bi-eye"></i>
                            </a>
                            <a href="/courses/${c.id}/edit" class="btn btn-sm btn-outline-primary btn-icon" title="Редагувати">
                                <i class="bi bi-pencil"></i>
                            </a>
                            <form method="post" action="/courses/${c.id}/delete" class="d-inline"
                                  onsubmit="return confirm('Видалити курс?')">
                                <button class="btn btn-sm btn-outline-danger btn-icon" title="Видалити">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
                </#list>
            <#else>
                <tr><td colspan="5">
                    <div class="empty-state text-center">
                        <i class="bi bi-book fs-1 d-block mb-2"></i>Курсів поки немає
                    </div>
                </td></tr>
            </#if>
        </tbody>
    </table>
</div>

<#include "../_footer.ftlh">
