create table if not exists t_user (
    user_id bigint primary key,
    nickname text not null,
    pwd_hash text not null,
    add_date timestamptz not null default now(),
    upd_date timestamptz not null default now()
);

create table if not exists t_user_session (
    user_id bigint primary key,
    next_token text,
    token_limit_date timestamptz,
    always_login_flg int not null,
    add_date timestamptz not null default now(),
    upd_date timestamptz not null default now()
);

create table if not exists t_user_reminder (
    reminder_id bigserial primary key,
    user_id bigint not null,
    title text not null,
    attach_file text,
    complete_flg int not null,
    add_date timestamptz not null default now(),
    upd_date timestamptz not null default now()
);