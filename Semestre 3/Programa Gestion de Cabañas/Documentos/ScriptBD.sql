/****** Object:  Table [dbo].[Cabanas]    Script Date: 8/5/2023 13:50:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cabanas](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [nvarchar](max) NULL,
	[Desc] [nvarchar](max) NULL,
	[HayJacuzzi] [bit] NOT NULL,
	[HayReserva] [bit] NOT NULL,
	[NumHabitacion] [int] NOT NULL,
	[CantPersMax] [int] NOT NULL,
	[Foto] [nvarchar](max) NULL,
	[TipoCabId] [int] NOT NULL,
 CONSTRAINT [PK_Cabanas] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Mantenimientos]    Script Date: 8/5/2023 13:50:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Mantenimientos](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Fecha] [datetime2](7) NOT NULL,
	[Desc] [nvarchar](max) NULL,
	[Costo] [int] NOT NULL,
	[Tecnico] [nvarchar](max) NULL,
	[CabanaId] [int] NOT NULL,
 CONSTRAINT [PK_Mantenimientos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TipoCabs]    Script Date: 8/5/2023 13:50:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TipoCabs](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [nvarchar](max) NOT NULL,
	[Desc] [nvarchar](max) NULL,
	[CostoP] [int] NOT NULL,
 CONSTRAINT [PK_TipoCabs] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 8/5/2023 13:50:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuarios](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [nvarchar](max) NOT NULL,
	[Pass] [nvarchar](max) NOT NULL,
	[Correo] [nvarchar](max) NOT NULL,
	[User] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[Cabanas]  WITH CHECK ADD  CONSTRAINT [FK_Cabanas_TipoCabs_TipoCabId] FOREIGN KEY([TipoCabId])
REFERENCES [dbo].[TipoCabs] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Cabanas] CHECK CONSTRAINT [FK_Cabanas_TipoCabs_TipoCabId]
GO
ALTER TABLE [dbo].[Mantenimientos]  WITH CHECK ADD  CONSTRAINT [FK_Mantenimientos_Cabanas_CabanaId] FOREIGN KEY([CabanaId])
REFERENCES [dbo].[Cabanas] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Mantenimientos] CHECK CONSTRAINT [FK_Mantenimientos_Cabanas_CabanaId]
GO
