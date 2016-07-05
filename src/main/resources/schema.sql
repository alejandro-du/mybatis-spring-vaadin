DROP TABLE IF EXISTS company;

CREATE TABLE company (
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    website VARCHAR(255)
);

INSERT INTO company(name, website) VALUES ('Vaadin', 'https://vaadin.com');
INSERT INTO company(name, website) VALUES ('Red Hat', 'https://www.redhat.com');
INSERT INTO company(name, website) VALUES ('Canonical', 'http://www.canonical.com');
INSERT INTO company(name, website) VALUES ('Sonatype', 'http://www.sonatype.com');
INSERT INTO company(name, website) VALUES ('Alfresco', 'https://www.alfresco.com');
