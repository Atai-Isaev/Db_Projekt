USE [master]
GO
/****** Object:  Database [Db_Projekt]    Script Date: 13.05.2021 20:31:18 ******/
CREATE DATABASE [Db_Projekt]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Db_Projekt', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Db_Projekt.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Db_Projekt_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Db_Projekt_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Db_Projekt] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Db_Projekt].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Db_Projekt] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Db_Projekt] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Db_Projekt] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Db_Projekt] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Db_Projekt] SET ARITHABORT OFF 
GO
ALTER DATABASE [Db_Projekt] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Db_Projekt] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Db_Projekt] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Db_Projekt] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Db_Projekt] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Db_Projekt] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Db_Projekt] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Db_Projekt] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Db_Projekt] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Db_Projekt] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Db_Projekt] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Db_Projekt] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Db_Projekt] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Db_Projekt] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Db_Projekt] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Db_Projekt] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Db_Projekt] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Db_Projekt] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Db_Projekt] SET  MULTI_USER 
GO
ALTER DATABASE [Db_Projekt] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Db_Projekt] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Db_Projekt] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Db_Projekt] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Db_Projekt] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Db_Projekt] SET QUERY_STORE = OFF
GO
ALTER DATABASE [Db_Projekt] SET  READ_WRITE 
GO

USE [Db_Projekt]
GO
/****** Object:  User [acc1]    Script Date: 06.06.2021 20:25:34 ******/
CREATE USER [acc1] FOR LOGIN [acc1] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [acc2]    Script Date: 06.06.2021 20:25:34 ******/
CREATE USER [acc2] FOR LOGIN [acc2] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [admin]    Script Date: 06.06.2021 20:25:34 ******/
CREATE USER [admin] FOR LOGIN [admin] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_datareader] ADD MEMBER [acc1]
GO
ALTER ROLE [db_datareader] ADD MEMBER [acc2]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [acc2]
GO
ALTER ROLE [db_owner] ADD MEMBER [admin]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 06.06.2021 20:25:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Artikel]    Script Date: 06.06.2021 20:25:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Artikel](
	[ArtikelNr] [int] IDENTITY(1,1) NOT NULL,
	[ArtikelName] [varchar](255) NOT NULL,
	[HerstellerNr] [int] NOT NULL,
	[KategorieNr] [int] NOT NULL,
	[Modelljahr] [smallint] NOT NULL,
	[Listenpreis] [decimal](10, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ArtikelNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bestände]    Script Date: 06.06.2021 20:25:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bestände](
	[GeschäftNr] [int] NOT NULL,
	[ArtikelNr] [int] NOT NULL,
	[Menge] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[GeschäftNr] ASC,
	[ArtikelNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bestellung]    Script Date: 06.06.2021 20:25:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bestellung](
	[BestellungNr] [int] IDENTITY(1,1) NOT NULL,
	[KundeNr] [int] NULL,
	[Bestellstatus] [tinyint] NOT NULL,
	[Bestelldatum] [date] NOT NULL,
	[Bedarfsdatum] [date] NOT NULL,
	[Versanddatum] [date] NULL,
	[GeschäftNr] [int] NOT NULL,
	[MitarbeiterNr] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[BestellungNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bestellung_Artikel]    Script Date: 06.06.2021 20:25:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bestellung_Artikel](
	[BestellungNr] [int] NOT NULL,
	[ArtikelNr] [int] NOT NULL,
	[Menge] [int] NOT NULL,
	[Listenpreis] [decimal](10, 2) NOT NULL,
	[Rabatt] [decimal](4, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[BestellungNr] ASC,
	[ArtikelNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Geschäft]    Script Date: 06.06.2021 20:25:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Geschäft](
	[GeschäftNr] [int] IDENTITY(1,1) NOT NULL,
	[GeschäftName] [varchar](255) NOT NULL,
	[Telefon] [varchar](25) NULL,
	[Email] [varchar](255) NULL,
	[Straße] [varchar](255) NULL,
	[Ort] [varchar](50) NULL,
	[PLZ] [varchar](5) NULL,
 CONSTRAINT [PK_Geschäft] PRIMARY KEY CLUSTERED 
(
	[GeschäftNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Hersteller]    Script Date: 06.06.2021 20:25:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hersteller](
	[HerstellerNr] [int] IDENTITY(1,1) NOT NULL,
	[HerstellerName] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[HerstellerNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Kategorie]    Script Date: 06.06.2021 20:25:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Kategorie](
	[KategorieNr] [int] IDENTITY(1,1) NOT NULL,
	[KategorieName] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[KategorieNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Kunde]    Script Date: 06.06.2021 20:25:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Kunde](
	[KundeNr] [int] IDENTITY(1,1) NOT NULL,
	[Vorname] [varchar](255) NOT NULL,
	[Nachname] [varchar](255) NOT NULL,
	[Telefon] [varchar](25) NULL,
	[Email] [varchar](255) NOT NULL,
	[Straße] [varchar](255) NULL,
	[Ort] [varchar](50) NULL,
	[PLZ] [varchar](5) NULL,
PRIMARY KEY CLUSTERED 
(
	[KundeNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Mitarbeiter]    Script Date: 06.06.2021 20:25:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Mitarbeiter](
	[MitarbeiterNr] [int] IDENTITY(1,1) NOT NULL,
	[Vorname] [varchar](50) NOT NULL,
	[Nachname] [varchar](50) NOT NULL,
	[Email] [varchar](255) NOT NULL,
	[Telefon] [varchar](25) NULL,
	[Aktiv] [tinyint] NOT NULL,
	[GeschäftNr] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MitarbeiterNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Bestellung_Artikel] ADD  DEFAULT ((0)) FOR [Rabatt]
GO
ALTER TABLE [dbo].[Artikel]  WITH CHECK ADD FOREIGN KEY([HerstellerNr])
REFERENCES [dbo].[Hersteller] ([HerstellerNr])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Artikel]  WITH CHECK ADD FOREIGN KEY([KategorieNr])
REFERENCES [dbo].[Kategorie] ([KategorieNr])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Bestände]  WITH CHECK ADD FOREIGN KEY([ArtikelNr])
REFERENCES [dbo].[Artikel] ([ArtikelNr])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Bestände]  WITH CHECK ADD FOREIGN KEY([GeschäftNr])
REFERENCES [dbo].[Geschäft] ([GeschäftNr])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Bestellung]  WITH CHECK ADD FOREIGN KEY([GeschäftNr])
REFERENCES [dbo].[Geschäft] ([GeschäftNr])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Bestellung]  WITH CHECK ADD FOREIGN KEY([KundeNr])
REFERENCES [dbo].[Kunde] ([KundeNr])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Bestellung]  WITH CHECK ADD FOREIGN KEY([MitarbeiterNr])
REFERENCES [dbo].[Mitarbeiter] ([MitarbeiterNr])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Bestellung_Artikel]  WITH CHECK ADD FOREIGN KEY([ArtikelNr])
REFERENCES [dbo].[Artikel] ([ArtikelNr])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Bestellung_Artikel]  WITH CHECK ADD FOREIGN KEY([BestellungNr])
REFERENCES [dbo].[Bestellung] ([BestellungNr])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Mitarbeiter]  WITH CHECK ADD FOREIGN KEY([GeschäftNr])
REFERENCES [dbo].[Geschäft] ([GeschäftNr]) 
ON UPDATE CASCADE
ON DELETE CASCADE
GO

USE [Db_Projekt]
GO
SET IDENTITY_INSERT [dbo].[Geschäft] ON 

INSERT [dbo].[Geschäft] ([GeschäftNr], [GeschäftName], [Telefon], [Email], [Straße], [Ort], [PLZ]) VALUES (1, N'Saturn', N'19282725', N'sat@gmail.com', N'Saturnstrasse', N'Zwickau', N'08056')
INSERT [dbo].[Geschäft] ([GeschäftNr], [GeschäftName], [Telefon], [Email], [Straße], [Ort], [PLZ]) VALUES (2, N'Globus', N'127253748', N'glob@gmail.com', N'Globusstrasse', N'Zwickau', N'08056')
INSERT [dbo].[Geschäft] ([GeschäftNr], [GeschäftName], [Telefon], [Email], [Straße], [Ort], [PLZ]) VALUES (3, N'Mediamarkt', N'264521728', N'med@gmail.com', N'Mediastrasse', N'Zwickau', N'08056')
SET IDENTITY_INSERT [dbo].[Geschäft] OFF
GO
SET IDENTITY_INSERT [dbo].[Mitarbeiter] ON 

INSERT [dbo].[Mitarbeiter] ([MitarbeiterNr], [Vorname], [Nachname], [Email], [Telefon], [Aktiv], [GeschäftNr]) VALUES (1, N'Reiner', N'Robert', N'meathead@lola.com', N'19587238', 1, 1)
INSERT [dbo].[Mitarbeiter] ([MitarbeiterNr], [Vorname], [Nachname], [Email], [Telefon], [Aktiv], [GeschäftNr]) VALUES (2, N'Grunert', N'Paul', N'paul@briefmarken-hamburg.de', N'47294978', 1, 2)
INSERT [dbo].[Mitarbeiter] ([MitarbeiterNr], [Vorname], [Nachname], [Email], [Telefon], [Aktiv], [GeschäftNr]) VALUES (3, N'Weinberger', N'Eduard', N'weinberger@lola.com', N'10275748', 1, 3)
INSERT [dbo].[Mitarbeiter] ([MitarbeiterNr], [Vorname], [Nachname], [Email], [Telefon], [Aktiv], [GeschäftNr]) VALUES (4, N'Brisebois', N'Danielle', N'danielle@tricatel.fr', N'27685710', 1, 1)
SET IDENTITY_INSERT [dbo].[Mitarbeiter] OFF
GO
SET IDENTITY_INSERT [dbo].[Kunde] ON 

INSERT [dbo].[Kunde] ([KundeNr], [Vorname], [Nachname], [Telefon], [Email], [Straße], [Ort], [PLZ]) VALUES (1, N'Loewe', N'Arthur', N'99746227', N'adler@on-line.de', N'Sebastianstrasse 134', N'Koeln', N'50737')
INSERT [dbo].[Kunde] ([KundeNr], [Vorname], [Nachname], [Telefon], [Email], [Straße], [Ort], [PLZ]) VALUES (2, N'Adler', N'Felix', N'9856023452', N'johnny@hip.de', N'Goethestrasse 4', N'Hannover', N'30453')
INSERT [dbo].[Kunde] ([KundeNr], [Vorname], [Nachname], [Telefon], [Email], [Straße], [Ort], [PLZ]) VALUES (3, N'Falkner', N'Michael', N'13726583', N'mischa24@wysiwyg.com', N'Querfeldstrasse 21', N'Wiesbaden', N'65187')
SET IDENTITY_INSERT [dbo].[Kunde] OFF
GO
SET IDENTITY_INSERT [dbo].[Bestellung] ON 

INSERT [dbo].[Bestellung] ([BestellungNr], [KundeNr], [Bestellstatus], [Bestelldatum], [Bedarfsdatum], [Versanddatum], [GeschäftNr], [MitarbeiterNr]) VALUES (1, 1, 2, CAST(N'2021-05-13' AS Date), CAST(N'2021-05-14' AS Date), CAST(N'2021-05-13' AS Date), 1, 1)
INSERT [dbo].[Bestellung] ([BestellungNr], [KundeNr], [Bestellstatus], [Bestelldatum], [Bedarfsdatum], [Versanddatum], [GeschäftNr], [MitarbeiterNr]) VALUES (2, 2, 2, CAST(N'2021-05-11' AS Date), CAST(N'2021-05-12' AS Date), CAST(N'2021-05-11' AS Date), 2, 2)
INSERT [dbo].[Bestellung] ([BestellungNr], [KundeNr], [Bestellstatus], [Bestelldatum], [Bedarfsdatum], [Versanddatum], [GeschäftNr], [MitarbeiterNr]) VALUES (3, 3, 4, CAST(N'2021-05-10' AS Date), CAST(N'2021-05-11' AS Date), CAST(N'2021-05-10' AS Date), 3, 3)
SET IDENTITY_INSERT [dbo].[Bestellung] OFF
GO
SET IDENTITY_INSERT [dbo].[Kategorie] ON 

INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (1, N'Monitore')
INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (2, N'Scanner')
INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (3, N'Grafikkarten')
INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (4, N'Festplatten')
INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (5, N'Soundkarten')
INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (6, N'Keyboards')
INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (7, N'Drucker')
INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (8, N'Software')
INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (9, N'Laufwerke')
INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (10, N'Mause')
INSERT [dbo].[Kategorie] ([KategorieNr], [KategorieName]) VALUES (11, N'3,5-Zoll-Disketten')
SET IDENTITY_INSERT [dbo].[Kategorie] OFF
GO
SET IDENTITY_INSERT [dbo].[Hersteller] ON 

INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (1, N'Belinea')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (2, N'Samsung')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (3, N'Sony')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (4, N'HP')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (5, N'Matrox')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (6, N'Terratec')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (7, N'Canon')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (8, N'Epson')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (9, N'Fujitsu')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (10, N'Microsoft')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (11, N'Asus')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (12, N'Acer')
INSERT [dbo].[Hersteller] ([HerstellerNr], [HerstellerName]) VALUES (13, N'Dell')
SET IDENTITY_INSERT [dbo].[Hersteller] OFF
GO
SET IDENTITY_INSERT [dbo].[Artikel] ON 

INSERT [dbo].[Artikel] ([ArtikelNr], [ArtikelName], [HerstellerNr], [KategorieNr], [Modelljahr], [Listenpreis]) VALUES (1, N'106075', 1, 1, 2017, CAST(137.93 AS Decimal(10, 2)))
INSERT [dbo].[Artikel] ([ArtikelNr], [ArtikelName], [HerstellerNr], [KategorieNr], [Modelljahr], [Listenpreis]) VALUES (2, N'CanoScan Li 20', 4, 1, 2020, CAST(47.41 AS Decimal(10, 2)))
SET IDENTITY_INSERT [dbo].[Artikel] OFF
GO
INSERT [dbo].[Bestände] ([GeschäftNr], [ArtikelNr], [Menge]) VALUES (1, 1, 10)
INSERT [dbo].[Bestände] ([GeschäftNr], [ArtikelNr], [Menge]) VALUES (2, 2, 15)
GO
INSERT [dbo].[Bestellung_Artikel] ([BestellungNr], [ArtikelNr], [Menge], [Listenpreis], [Rabatt]) VALUES (1, 1, 1, CAST(15.20 AS Decimal(10, 2)), CAST(10.00 AS Decimal(4, 2)))
INSERT [dbo].[Bestellung_Artikel] ([BestellungNr], [ArtikelNr], [Menge], [Listenpreis], [Rabatt]) VALUES (2, 2, 2, CAST(30.50 AS Decimal(10, 2)), CAST(0.00 AS Decimal(4, 2)))
GO
INSERT [dbo].[Account] ([username], [password]) VALUES (N'acc1', N'acc1')
INSERT [dbo].[Account] ([username], [password]) VALUES (N'acc2', N'acc2')
INSERT [dbo].[Account] ([username], [password]) VALUES (N'admin', N'admin')
GO
