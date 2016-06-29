CREATE TABLE COMPANY (
    ID INT IDENTITY,
    NAME VARCHAR(255) NOT NULL,
    WEBSITE VARCHAR(255)
);

INSERT INTO COMPANY(NAME, WEBSITE) VALUES ('Vaadin', 'https://vaadin.com');
INSERT INTO COMPANY(NAME, WEBSITE) VALUES ('Red Hat', 'https://www.redhat.com');
INSERT INTO COMPANY(NAME, WEBSITE) VALUES ('Canonical', 'http://www.canonical.com');
INSERT INTO COMPANY(NAME, WEBSITE) VALUES ('Sonatype', 'http://www.sonatype.com');
INSERT INTO COMPANY(NAME, WEBSITE) VALUES ('Alfresco', 'https://www.alfresco.com');
