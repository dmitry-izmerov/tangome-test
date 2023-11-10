-- There is the table schema below that stores "view" events from users from different regions.
-- Need to write query to show 5 regions with biggest number of unique "views" for the last month sorted by these numbers descending.
-- It is possible to update schema to speed up query.
create table viewers_stats (
    id BIGINT NOT NULL AUTO_INCREMENT,
    account_id BIGINT NOT NULL,
    region VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL default CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
);

-- query:
SELECT region, COUNT(DISTINCT account_id) AS accounts_count
    FROM viewers_stats
    WHERE created >= DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)
    GROUP BY region
    ORDER BY accounts_count DESC
    LIMIT 5;

-- performance improvement:
CREATE INDEX idx_created_region ON viewers_stats (created, region);