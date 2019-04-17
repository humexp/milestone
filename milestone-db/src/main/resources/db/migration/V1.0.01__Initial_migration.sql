CREATE TABLE "${schema}".projects (
  id              VARCHAR(16) UNIQUE NOT NULL,
  name            VARCHAR(255) NOT NULL
);

CREATE TABLE "${schema}".tasks (
  id                    VARCHAR(16) UNIQUE NOT NULL,
  project               VARCHAR(16) REFERENCES "${schema}".projects (id) NOT NULL,
  parent_task           VARCHAR(16) REFERENCES "${schema}".tasks (id),
  title                 VARCHAR(255) NOT NULL,
  body                  VARCHAR(255),
  expected_effort       INTEGER
);

CREATE TABLE "${schema}".efforts (
  id              VARCHAR(16) UNIQUE NOT NULL,
  task            VARCHAR(16) REFERENCES "${schema}".tasks (id) NOT NULL,
  date            BIGINT NOT NULL,
  effort          INTEGER NOT NULL
);

CREATE TABLE "${schema}".users (
  id              VARCHAR(16) UNIQUE NOT NULL,
  login           VARCHAR(255) UNIQUE NOT NULL,
  name            VARCHAR(255) NOT NULL
);

CREATE TABLE "${schema}".user_to_project (
  user_id          VARCHAR(16) REFERENCES "${schema}".users (id) NOT NULL,
  project_id       VARCHAR(16) REFERENCES "${schema}".projects (id) NOT NULL
);

CREATE TABLE "${schema}".user_to_task (
  user_id          VARCHAR(16) REFERENCES "${schema}".users (id) NOT NULL,
  task_id          VARCHAR(16) REFERENCES "${schema}".tasks (id) NOT NULL
);
