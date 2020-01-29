create table entries (
    id bigserial not null,
    title varchar not null,
    entry varchar not null,
    date timestamp not null,
    owner_username varchar not null,

    primary key (id),
    foreign key (owner_username) references profile(username)
);