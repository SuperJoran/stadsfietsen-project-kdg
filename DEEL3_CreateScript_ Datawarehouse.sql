CREATE SCHEMA datawarehouse;

# Create Datum Dimensie Table
CREATE TABLE datawarehouse.STADSFIETSEN_DATE_DIMENSION
(
  DATE_SK INT
, year_number INT
, month_number INT
, day_of_year_number INT
, day_of_month_number INT
, day_of_week_number INT
, quarter_number INT
, quarter_name VARCHAR(2)
, year_quarter_name VARCHAR(32)
, DayOfWeekDesc VARCHAR(30)
, DayOfWeekShortDesc VARCHAR(3)
, DayofWeekendInd CHAR(1)
, MonthDesc VARCHAR(30)
, MonthShortDesc VARCHAR(3)
, week_of_month_number INT
, week_of_month_name TINYTEXT
, week_name VARCHAR(32)
, DAY_OF_WEEK_SORT_NAME VARCHAR(60)
, DAY_DESC TINYTEXT
, Event VARCHAR(12)
)
;CREATE INDEX idx_STADSFIETSEN_DATE_DIMENSION_lookup ON datawarehouse.STADSFIETSEN_DATE_DIMENSION(DATE_SK)
;


# Create Klant Dimensie Table
CREATE TABLE STADSFIETSEN_KLANT_DIMENSION
(
  KlantDimensionID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY
, version INT
, date_from DATETIME
, date_to DATETIME
, Id INT
, naam VARCHAR(255)
, postCode INT
)
;CREATE INDEX idx_STADSFIETSEN_KLANT_DIMENSION_lookup ON STADSFIETSEN_KLANT_DIMENSION(Id)
;
CREATE INDEX idx_STADSFIETSEN_KLANT_DIMENSION_tk ON STADSFIETSEN_KLANT_DIMENSION(KlantDimensionID)
;


# Create Regen Dimensie Table
CREATE TABLE STADSFIETSEN_REGEN_DIMENSION
(
  Id VARCHAR(15)
, DATUM VARCHAR(15)
, UUR VARCHAR(5)
, number BIGINT
, omschrijving VARCHAR(27)
)
;CREATE INDEX idx_STADSFIETSEN_REGEN_DIMENSION_lookup ON STADSFIETSEN_REGEN_DIMENSION(Id)
;


#Create Rit Fact Table
CREATE TABLE datawarehouse.STADSFIETSEN_FACT_RIT
(
  ritId INT
, ritDatum VARCHAR(10)
, aantalMinuten BIGINT
, DATE_SK INT
, klantDimensionId BIGINT
, regendimensieId VARCHAR(15)
, creatieDatum DATETIME
)
;CREATE INDEX idx_STADSFIETSEN_FACT_RIT_lookup ON datawarehouse.STADSFIETSEN_FACT_RIT(ritId)
;


