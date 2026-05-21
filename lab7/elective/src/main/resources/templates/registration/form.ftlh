<#include "../_header.ftlh">

<div class="mb-4">
    <a href="/registrations" class="text-muted text-decoration-none small">
        <i class="bi bi-arrow-left me-1"></i>Записи
    </a>
    <h1 class="page-title mt-1">Записати студента на курс</h1>
</div>

<div class="row justify-content-center">
    <div class="col-md-6 col-lg-5">
        <div class="card">
            <div class="card-body p-4">
                <form method="post" action="/registrations">
                    <div class="form-floating mb-3">
                        <select id="studentId" name="studentId" class="form-select" required>
                            <option value="">— Оберіть студента —</option>
                            <#if students?has_content>
                                <#list students as s>
                                    <option value="${s.id}"
                                        ${(selectedStudentId?? && selectedStudentId == s.id)?then("selected", "")}>
                                        ${s.fullName} (${s.courseYear} курс)
                                    </option>
                                </#list>
                            </#if>
                        </select>
                        <label for="studentId">Студент *</label>
                    </div>
                    <div class="form-floating mb-4">
                        <select id="courseId" name="courseId" class="form-select" required>
                            <option value="">— Оберіть курс —</option>
                            <#if courses?has_content>
                                <#list courses as c>
                                    <option value="${c.id}"
                                        ${(selectedCourseId?? && selectedCourseId == c.id)?then("selected", "")}>
                                        ${c.name}<#if c.teacher??> · ${c.teacher.fullName}</#if>
                                    </option>
                                </#list>
                            </#if>
                        </select>
                        <label for="courseId">Курс *</label>
                    </div>
                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary flex-grow-1">
                            <i class="bi bi-check-lg me-1"></i>Записати
                        </button>
                        <a href="/registrations" class="btn btn-outline-secondary">Скасувати</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<#include "../_footer.ftlh">
