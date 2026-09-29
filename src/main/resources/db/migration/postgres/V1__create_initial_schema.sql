-- ============================================================
-- AUTH
-- ============================================================

CREATE TABLE auth (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);


-- ============================================================
-- EVENTS
-- ============================================================

CREATE TABLE tb_event (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    category VARCHAR(255) NOT NULL,

    start_date DATE NOT NULL,
    end_date DATE NOT NULL,

    start_time TIME NOT NULL,
    close_time TIME NOT NULL
);


-- ============================================================
-- EVENT IMAGES
-- ============================================================

CREATE TABLE tb_event_images (
    event_id BIGINT NOT NULL,
    images_urls VARCHAR(255),

    CONSTRAINT fk_event_images_event
        FOREIGN KEY (event_id)
        REFERENCES tb_event(id)
        ON DELETE CASCADE
);


-- ============================================================
-- PROJECTS
-- ============================================================

CREATE TABLE tb_project (
    id BIGSERIAL PRIMARY KEY,

    event_id BIGINT NOT NULL,
    user_id UUID NOT NULL,

    project_name VARCHAR(255),
    video_url VARCHAR(255),
    summary VARCHAR(1000),
    description VARCHAR(5000),

    status VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,

    CONSTRAINT fk_project_event
        FOREIGN KEY (event_id)
        REFERENCES tb_event(id),

    CONSTRAINT fk_project_user
        FOREIGN KEY (user_id)
        REFERENCES auth(id)
);


-- ============================================================
-- PROJECT AUTHORS
-- ============================================================

CREATE TABLE project_authors (
    project_id BIGINT NOT NULL,
    name VARCHAR(255),

    CONSTRAINT fk_project_authors_project
        FOREIGN KEY (project_id)
        REFERENCES tb_project(id)
        ON DELETE CASCADE
);


-- ============================================================
-- USER LIKES
-- ============================================================

CREATE TABLE user_likes (
    user_id UUID NOT NULL,
    project_id BIGINT NOT NULL,

    CONSTRAINT pk_user_likes
        PRIMARY KEY (user_id, project_id),

    CONSTRAINT fk_user_likes_user
        FOREIGN KEY (user_id)
        REFERENCES auth(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_likes_project
        FOREIGN KEY (project_id)
        REFERENCES tb_project(id)
        ON DELETE CASCADE
);


-- ============================================================
-- USER PROJECTS
-- ============================================================

CREATE TABLE user_projects (
    user_id UUID NOT NULL,
    project_id BIGINT NOT NULL,

    CONSTRAINT pk_user_projects
        PRIMARY KEY (user_id, project_id),

    CONSTRAINT fk_user_projects_user
        FOREIGN KEY (user_id)
        REFERENCES auth(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_projects_project
        FOREIGN KEY (project_id)
        REFERENCES tb_project(id)
        ON DELETE CASCADE
);


-- ============================================================
-- COMMENTS
-- ============================================================

CREATE TABLE tb_comments (
    id BIGSERIAL PRIMARY KEY,

    project_id BIGINT NOT NULL,
    user_id UUID NOT NULL,

    comment_message VARCHAR(300) NOT NULL,

    CONSTRAINT fk_comment_project
        FOREIGN KEY (project_id)
        REFERENCES tb_project(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_comment_user
        FOREIGN KEY (user_id)
        REFERENCES auth(id)
        ON DELETE CASCADE
);


-- ============================================================
-- INDEXES
-- ============================================================

CREATE INDEX idx_project_event
    ON tb_project(event_id);

CREATE INDEX idx_project_user
    ON tb_project(user_id);

CREATE INDEX idx_comments_project
    ON tb_comments(project_id);

CREATE INDEX idx_comments_user
    ON tb_comments(user_id);

CREATE INDEX idx_event_images_event
    ON tb_event_images(event_id);

CREATE INDEX idx_project_authors_project
    ON project_authors(project_id);

CREATE INDEX idx_user_likes_project
    ON user_likes(project_id);

CREATE INDEX idx_user_projects_project
    ON user_projects(project_id);
