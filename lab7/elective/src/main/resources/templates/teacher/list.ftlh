<#include "../_header.ftlh">

<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <h1 class="page-title">Викладачі</h1>
        <p class="text-muted mb-0 small">Список викладачів факультативних курсів</p>
    </div>
    <a href="/teachers/new" class="btn btn-primary">
        <i class="bi bi-person-plus-fill me-1"></i>Додати
    </a>
</div>

<div class="card table-card">
    <table class="table mb-0">
        <thead>
            <tr><th>#</th><th>Викладач</th><th>Email</th><th>Курсів</th><th></th></tr>
        </thead>
        <tbody>
            <#if teachers?has_content>
                <#list teachers as t>
                <tr>
                    <td class="text-muted small">${t.id}</td>
                    <td>
                        <div class="d-flex align-items-center gap-2">
                            <span class="d-inline-flex align-items-center justify-content-center rounded-circle fw-bold"
                                  style="width:34px;height:34px;font-size:0.8rem;flex-shrink:0;background:#f3e8ff;color:#7c3aed;">
                                ${t.firstName?substring(0,1)}${t.lastName?substring(0,1)}
                            </span>
                            ${t.fullName}
                        </div>
                    </td>
                    <td class="text-muted">${t.email}</td>
                    <td><span class="badge" style="background:#f3e8ff;color:#7c3aed;">${t.courses?size} курс(ів)</span></td>
                    <td class="text-end">
                        <div class="d-flex gap-1 justify-content-end">
                            <a href="/teachers/${t.id}/edit" class="btn btn-sm btn-outline-primary btn-icon" title="Редагувати">
                                <i class="bi bi-pencil"></i>
                            </a>
                            <form method="post" action="/teachers/${t.id}/delete" class="d-inline"
                                  onsubmit="return confirm('Видалити викладача ${t.fullName}?')">
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
                        <i class="bi bi-person-badge fs-1 d-block mb-2"></i>
                        Викладачів поки немає
                    </div>
                </td></tr>
            </#if>
        </tbody>
    </table>
</div>

<#include "../_footer.ftlh">
