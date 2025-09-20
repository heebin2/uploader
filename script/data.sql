CREATE TABLE extension_filter
(
    delete_yn             char(1)     NOT NULL,
    use_yn                char(1)     NOT NULL,
    extension_filter_type varchar(6)  NOT NULL,
    created_datetime      timestamp,
    id                    integer
        PRIMARY KEY,
    modified_datetime     timestamp,
    name                  varchar(20) NOT NULL,
    CHECK (delete_yn IN ('N', 'Y')),
    CHECK (extension_filter_type IN ('FIXED', 'CUSTOM')),
    CHECK (use_yn IN ('N', 'Y'))
);

CREATE INDEX extension_filter_name_idx
    ON extension_filter (name);

INSERT INTO extension_filter (name, extension_filter_type, use_yn, delete_yn)
VALUES ('bat', 'FIXED', 'Y', 'N'),
       ('cmd', 'FIXED', 'Y', 'N'),
       ('com', 'FIXED', 'Y', 'N'),
       ('cpl', 'FIXED', 'Y', 'N'),
       ('exe', 'FIXED', 'Y', 'N'),
       ('scr', 'FIXED', 'Y', 'N'),
       ('js', 'FIXED', 'Y', 'N');