<#include "_header.ftlh">

<div class="mb-4">
    <h1 class="page-title">Головна</h1>
    <p class="text-muted mb-0">Управління факультативними курсами університету</p>
</div>

<!-- Статистика -->
<div class="row g-3 mb-4">
    <div class="col-6 col-lg-4">
        <div class="card stat-card h-100">
            <div class="card-body d-flex align-items-center gap-3">
                <div class="stat-icon bg-primary bg-opacity-10 text-primary">
                    <i class="bi bi-people-fill"></i>
                </div>
                <div>
                    <div class="stat-num text-primary">${studentCount}</div>
                    <div class="stat-label">Студентів</div>
                </div>
                <a href="/students" class="stretched-link"></a>
            </div>
        </div>
    </div>
    <div class="col-6 col-lg-4">
        <div class="card stat-card h-100">
            <div class="card-body d-flex align-items-center gap-3">
                <div class="stat-icon bg-purple bg-opacity-10" style="background:#f3e8ff; color:#7c3aed;">
                    <i class="bi bi-person-badge-fill"></i>
                </div>
                <div>
                    <div class="stat-num" style="color:#7c3aed;">${teacherCount}</div>
                    <div class="stat-label">Викладачів</div>
                </div>
                <a href="/teachers" class="stretched-link"></a>
            </div>
        </div>
    </div>
    <div class="col-6 col-lg-4">
        <div class="card stat-card h-100">
            <div class="card-body d-flex align-items-center gap-3">
                <div class="stat-icon" style="background:#e0f2fe; color:#0284c7;">
                    <i class="bi bi-book-fill"></i>
                </div>
                <div>
                    <div class="stat-num" style="color:#0284c7;">${courseCount}</div>
                    <div class="stat-label">Курсів</div>
                </div>
                <a href="/courses" class="stretched-link"></a>
            </div>
        </div>
    </div>
    <div class="col-6 col-lg-6">
        <div class="card stat-card h-100">
            <div class="card-body d-flex align-items-center gap-3">
                <div class="stat-icon" style="background:#fef3c7; color:#d97706;">
                    <i class="bi bi-clipboard-check-fill"></i>
                </div>
                <div>
                    <div class="stat-num" style="color:#d97706;">${registrationCount}</div>
                    <div class="stat-label">Активних записів</div>
                </div>
                <a href="/registrations" class="stretched-link"></a>
            </div>
        </div>
    </div>
    <div class="col-6 col-lg-6">
        <div class="card stat-card h-100">
            <div class="card-body d-flex align-items-center gap-3">
                <div class="stat-icon" style="background:#dcfce7; color:#16a34a;">
                    <i class="bi bi-patch-check-fill"></i>
                </div>
                <div>
                    <div class="stat-num" style="color:#16a34a;">${archiveCount}</div>
                    <div class="stat-label">Оцінок в архіві</div>
                </div>
                <a href="/archive" class="stretched-link"></a>
            </div>
        </div>
    </div>
</div>

<!-- Дії -->
<div class="row g-3">
    <div class="col-md-6">
        <div class="card h-100">
            <div class="card-header">
                <i class="bi bi-lightning-fill text-warning me-2"></i>Швидкі дії
            </div>
            <div class="list-group list-group-flush rounded-bottom">
                <a href="/students/new" class="list-group-item list-group-item-action d-flex align-items-center gap-2 py-3">
                    <span class="stat-icon bg-primary bg-opacity-10 text-primary" style="width:36px;height:36px;border-radius:9px;font-size:1rem;">
                        <i class="bi bi-person-plus-fill"></i>
                    </span>
                    <div>
                        <div class="fw-600" style="font-weight:600;">Додати студента</div>
                        <div class="text-muted small">Зареєструвати нового студента</div>
                    </div>
                    <i class="bi bi-chevron-right ms-auto text-muted small"></i>
                </a>
                <a href="/teachers/new" class="list-group-item list-group-item-action d-flex align-items-center gap-2 py-3">
                    <span class="stat-icon" style="width:36px;height:36px;border-radius:9px;font-size:1rem;background:#f3e8ff;color:#7c3aed;">
                        <i class="bi bi-person-plus-fill"></i>
                    </span>
                    <div>
                        <div style="font-weight:600;">Додати викладача</div>
                        <div class="text-muted small">Зареєструвати нового викладача</div>
                    </div>
                    <i class="bi bi-chevron-right ms-auto text-muted small"></i>
                </a>
                <a href="/courses/new" class="list-group-item list-group-item-action d-flex align-items-center gap-2 py-3">
                    <span class="stat-icon" style="width:36px;height:36px;border-radius:9px;font-size:1rem;background:#e0f2fe;color:#0284c7;">
                        <i class="bi bi-plus-lg"></i>
                    </span>
                    <div>
                        <div style="font-weight:600;">Додати курс</div>
                        <div class="text-muted small">Створити новий факультатив</div>
                    </div>
                    <i class="bi bi-chevron-right ms-auto text-muted small"></i>
                </a>
                <a href="/registrations/new" class="list-group-item list-group-item-action d-flex align-items-center gap-2 py-3">
                    <span class="stat-icon" style="width:36px;height:36px;border-radius:9px;font-size:1rem;background:#fef3c7;color:#d97706;">
                        <i class="bi bi-pencil-fill"></i>
                    </span>
                    <div>
                        <div style="font-weight:600;">Записати студента</div>
                        <div class="text-muted small">Зареєструвати на курс</div>
                    </div>
                    <i class="bi bi-chevron-right ms-auto text-muted small"></i>
                </a>
                <a href="/archive/grade" class="list-group-item list-group-item-action d-flex align-items-center gap-2 py-3">
                    <span class="stat-icon" style="width:36px;height:36px;border-radius:9px;font-size:1rem;background:#dcfce7;color:#16a34a;">
                        <i class="bi bi-award-fill"></i>
                    </span>
                    <div>
                        <div style="font-weight:600;">Виставити оцінку</div>
                        <div class="text-muted small">Завершити курс для студента</div>
                    </div>
                    <i class="bi bi-chevron-right ms-auto text-muted small"></i>
                </a>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="card h-100">
            <div class="card-header">
                <i class="bi bi-info-circle-fill text-primary me-2"></i>Про систему
            </div>
            <div class="card-body">
                <p class="text-muted small mb-3">
                    Система дозволяє керувати факультативними курсами: реєструвати студентів,
                    призначати викладачів, відслідковувати записи та зберігати оцінки в архіві.
                </p>
                <div class="d-flex flex-column gap-2 small">
                    <div class="d-flex align-items-center gap-2">
                        <i class="bi bi-check2-circle text-success"></i>
                        <span>Повне CRUD управління сутностями</span>
                    </div>
                    <div class="d-flex align-items-center gap-2">
                        <i class="bi bi-check2-circle text-success"></i>
                        <span>GoF патерни: Strategy, Builder, Command, Factory, Observer</span>
                    </div>
                    <div class="d-flex align-items-center gap-2">
                        <i class="bi bi-check2-circle text-success"></i>
                        <span>FreeMarker шаблони, UTF-8, Spring MVC</span>
                    </div>
                    <div class="d-flex align-items-center gap-2">
                        <i class="bi bi-check2-circle text-success"></i>
                        <span>Spring Boot Actuator для профілювання</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "_footer.ftlh">
