-- もしデータベースにemployeeというテーブルが存在していなければ、
-- 文字列サイズ50文字のカラム名idと文字列サイズ50文字のカラム名nameと整数のageのデータを格納できるemployeeテーブルを作成する
CREATE TABLE IF NOT EXISTS employee (
	id VARCHAR(50) PRIMARY KEY,
	name VARCHAR(50),
	age INT
);