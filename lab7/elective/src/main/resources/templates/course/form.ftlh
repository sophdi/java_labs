<#include "../_header.ftlh">

<div class="mb-4">
    <a href="/courses" class="text-muted text-decoration-none small">
        <i class="bi bi-arrow-left me-1"></i>Курси
    </a>
    <h1 class="page-title mt-1">${(course.id??)?then("Редагувати курс", "Новий курс")}</h1>
</div>

<div class="row justify-content-center">
    <div class="col-md-7 col-lg-6">
        <div class="card">
            <div class="card-body p-4">
                <#assign action = (course.id??)?then("/courses/${course.id}/edit", "/courses")>
                <form method="post" action="${action}">
                    <div class="form-floating mb-3">
                        <input type="text" id="name" name="name" class="form-control"
                               placeholder="Назва курсу" value="${(course.name)!}" required>
                        <label for="name">Назва курсу *</label>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Опис</label>
                        <textarea id="description" name="description" class="form-control"
                                  rows="3" placeholder="Короткий опис курсу">${(course.description)!}</textarea>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" id="durationWeeks" name="durationWeeks" class="form-control"
                               placeholder="16" value="${(course.durationWeeks)!16}" min="1" max="52" required>
                        <label for="durationWeeks">Тривалість (тижнів) *</label>
                    </div>
                    <div class="form-floating mb-4">
                        <select id="teacherId" name="teacherId" class="form-select">
                            <option value="">— Без викладача —</option>
                            <#if teachers?has_content>
                                <#list teachers as t>
                                    <option value="${t.id}"
                                        ${(course.teacher?? && course.teacher.id == t.id)?then("selected", "")}>
                                        ${t.fullName}
                                    </option>
                                </#list>
                            </#if>
                        </select>
                        <label for="teacherId">Викладач</label>
                    </div>
                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary flex-grow-1">
                            <i class="bi bi-check-lg me-1"></i>Зберегти
                        </button>
                        <a href="/courses" class="btn btn-outline-secondary">Скасувати</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<#include "../_footer.ftlh">
