-- For MariaDB 10 and above only

--
-- Table structure for `eb_subscribers`
--

CREATE TABLE `eb_subscribers` (
  `uid` int(10) NOT NULL,
  `topic` varchar(250) COLLATE utf8mb4_bin NOT NULL,
  `url` varchar(250) COLLATE utf8mb4_bin NOT NULL,
  `authorization` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Indices for `eb_subscribers`
--
ALTER TABLE `eb_subscribers` ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for `eb_subscribers`
--
ALTER TABLE `eb_subscribers` MODIFY `uid` int(10) NOT NULL AUTO_INCREMENT;

--
-- Table structure for `eb_tokens`
--

CREATE TABLE `eb_tokens` (
  `uid` int(10) NOT NULL,
  `token` varchar(250) COLLATE utf8mb4_bin NOT NULL,
  `expiry_date` TIMESTAMP NOT NULL,
  `permissions` TEXT COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Indices for `eb_tokens`
--
ALTER TABLE `eb_tokens` ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for `eb_tokens`
--
ALTER TABLE `eb_tokens` MODIFY `uid` int(10) NOT NULL AUTO_INCREMENT;

