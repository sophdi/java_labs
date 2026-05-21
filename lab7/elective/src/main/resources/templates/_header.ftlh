<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Факультатив</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        :root {
            --body-bg: #f0f2f8;
            --card-radius: 16px;
            --nav-bg: #0f172a;
        }
        body {
            background: var(--body-bg);
            font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
            color: #1e293b;
        }
        /* ── Navbar ── */
        .navbar {
            background: var(--nav-bg) !important;
            padding: 0.7rem 1.25rem;
            box-shadow: 0 1px 0 rgba(255,255,255,0.06), 0 4px 20px rgba(0,0,0,0.2);
        }
        .navbar-brand {
            font-weight: 800;
            font-size: 1.2rem;
            letter-spacing: -0.5px;
        }
        .navbar-nav .nav-link {
            color: rgba(255,255,255,0.6) !important;
            border-radius: 8px;
            padding: 0.45rem 0.85rem !important;
            font-weight: 500;
            font-size: 0.9rem;
            transition: color 0.15s, background 0.15s;
        }
        .navbar-nav .nav-link:hover {
            color: #fff !important;
            background: rgba(255,255,255,0.08);
        }
        /* ── Cards ── */
        .card {
            border: none;
            border-radius: var(--card-radius);
            box-shadow: 0 1px 3px rgba(0,0,0,0.04), 0 4px 16px rgba(0,0,0,0.07);
            background: #fff;
        }
        .card-header {
            border-radius: var(--card-radius) var(--card-radius) 0 0 !important;
            border-bottom: 1px solid #f1f5f9;
            font-weight: 600;
            padding: 1rem 1.25rem;
            background: #fff;
        }
        .card-header.colored { color: #fff; border-bottom: none; }
        /* ── Stat cards ── */
        .stat-card .card-body { padding: 1.4rem; }
        .stat-icon {
            width: 52px; height: 52px;
            border-radius: 14px;
            display: flex; align-items: center; justify-content: center;
            font-size: 1.4rem; flex-shrink: 0;
        }
        .stat-num { font-size: 2rem; font-weight: 800; line-height: 1; }
        .stat-label { font-size: 0.8rem; font-weight: 500; color: #64748b; margin-top: 2px; }
        /* ── Tables ── */
        .table-card { overflow: hidden; }
        .table th {
            font-size: 0.72rem;
            text-transform: uppercase;
            letter-spacing: 0.6px;
            font-weight: 600;
            color: #94a3b8;
            background: #f8fafc;
            border-color: #f1f5f9;
            padding: 0.7rem 1.1rem;
        }
        .table td {
            padding: 0.85rem 1.1rem;
            vertical-align: middle;
            border-color: #f1f5f9;
        }
        .table tbody tr { transition: background 0.1s; }
        .table tbody tr:hover { background: #f8f9ff; }
        .table a { font-weight: 500; color: #4361ee; text-decoration: none; }
        .table a:hover { text-decoration: underline; }
        /* ── Buttons ── */
        .btn { border-radius: 10px; font-weight: 500; font-size: 0.9rem; }
        .btn-sm { border-radius: 7px; font-size: 0.8rem; }
        .btn-icon { width: 32px; height: 32px; padding: 0; display: inline-flex; align-items: center; justify-content: center; }
        /* ── Forms ── */
        .form-control, .form-select {
            border-radius: 10px;
            border-color: #e2e8f0;
            font-size: 0.95rem;
        }
        .form-control:focus, .form-select:focus {
            border-color: #4361ee;
            box-shadow: 0 0 0 3px rgba(67,97,238,0.12);
        }
        .form-floating > .form-control,
        .form-floating > .form-select { border-radius: 10px; }
        .form-label { font-weight: 500; font-size: 0.88rem; color: #475569; margin-bottom: 0.4rem; }
        /* ── Alerts ── */
        .alert { border: none; border-radius: 12px; font-size: 0.9rem; }
        /* ── Page header ── */
        .page-title { font-weight: 800; color: #0f172a; font-size: 1.6rem; }
        /* ── Badges ── */
        .badge { border-radius: 6px; font-weight: 600; font-size: 0.75rem; }
        /* ── Empty state ── */
        .empty-state { padding: 3rem 1rem; color: #94a3b8; }
        /* ── Footer ── */
        footer { margin-top: 4rem; padding: 1.5rem 0; border-top: 1px solid #e2e8f0; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark mb-4">
    <div class="container-lg">
        <a class="navbar-brand text-white" href="/">
            <i class="bi bi-mortarboard-fill text-warning me-2"></i>Факультатив
        </a>
        <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navMenu">
            <ul class="navbar-nav ms-auto gap-1">
                <li class="nav-item">
                    <a class="nav-link" href="/students"><i class="bi bi-people me-1"></i>Студенти</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/teachers"><i class="bi bi-person-badge me-1"></i>Викладачі</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/courses"><i class="bi bi-book me-1"></i>Курси</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registrations"><i class="bi bi-clipboard-check me-1"></i>Записи</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/archive"><i class="bi bi-archive me-1"></i>Архів</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-lg">

<#if success??>
<div class="alert alert-success d-flex align-items-center gap-2 mb-3">
    <i class="bi bi-check-circle-fill fs-5"></i>
    <span>${success}</span>
    <button type="button" class="btn-close ms-auto" data-bs-dismiss="alert"></button>
</div>
</#if>
<#if error??>
<div class="alert alert-danger d-flex align-items-center gap-2 mb-3">
    <i class="bi bi-exclamation-triangle-fill fs-5"></i>
    <span>${error}</span>
    <button type="button" class="btn-close ms-auto" data-bs-dismiss="alert"></button>
</div>
</#if>
