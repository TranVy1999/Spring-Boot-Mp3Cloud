USE [Mp3Cloud]
GO
/****** Object:  Table [dbo].[albums]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[albums](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[download_permit] [bit] NULL,
	[name] [varchar](255) NULL,
	[released_date] [datetime2](7) NULL,
	[total_tracks] [int] NULL,
	[generid] [int] NULL,
	[imageid] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[artist]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[artist](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nationality] [varchar](255) NULL,
	[birth_day] [datetime2](7) NULL,
	[description] [varchar](255) NULL,
	[gender] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[imageid] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[comment]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comment](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[contents] [varchar](255) NULL,
	[create_at] [varchar](255) NULL,
	[songid] [int] NULL,
	[userid] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[genre]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[genre](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[image]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[image](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[img_location] [varchar](255) NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[logs]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[logs](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[contents] [varchar](255) NULL,
	[create_at] [varchar](255) NULL,
	[modified] [varchar](255) NULL,
	[user_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[playlist]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[playlist](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
	[songid] [int] NULL,
	[userid] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[song]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[song](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[download_premit] [bit] NULL,
	[share_links] [varchar](255) NULL,
	[title] [varchar](255) NULL,
	[albumid] [int] NULL,
	[imageid] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sung_by]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sung_by](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[albumid] [int] NULL,
	[artistid] [int] NULL,
	[songid] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_type]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_type](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_position] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 12/26/2020 4:45:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[activity_status] [bit] NULL,
	[birth_day] [datetime2](7) NULL,
	[email] [varchar](255) NULL,
	[first_name] [varchar](255) NULL,
	[gender] [varchar](255) NULL,
	[last_name] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[user_typeid] [int] NULL,
	[user_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[artist] ON 

INSERT [dbo].[artist] ([id], [nationality], [birth_day], [description], [gender], [name], [imageid]) VALUES (1, N'Viet Nam', CAST(N'1999-04-20T00:00:00.0000000' AS DateTime2), N'The Queen Vpop', N'Nu', N'My Tam', NULL)
INSERT [dbo].[artist] ([id], [nationality], [birth_day], [description], [gender], [name], [imageid]) VALUES (2, N'Han Quoc', CAST(N'1996-12-12T00:00:00.0000000' AS DateTime2), N'Come from Korea', N'Nam', N'Super Junior', NULL)
INSERT [dbo].[artist] ([id], [nationality], [birth_day], [description], [gender], [name], [imageid]) VALUES (5, N'Viet Nam', CAST(N'1990-05-06T00:00:00.0000000' AS DateTime2), N'The Prince Vpop', N'Nam', N'Dam Vinh Hung', NULL)
INSERT [dbo].[artist] ([id], [nationality], [birth_day], [description], [gender], [name], [imageid]) VALUES (6, N'Viet Nam', CAST(N'1990-05-06T00:00:00.0000000' AS DateTime2), N'The Prince Vpop', N'Nam', N'Erik', NULL)
SET IDENTITY_INSERT [dbo].[artist] OFF
GO
SET IDENTITY_INSERT [dbo].[song] ON 

INSERT [dbo].[song] ([id], [download_premit], [share_links], [title], [albumid], [imageid]) VALUES (1, 1, N'tai sao the nho', N'why isn''t writing here', NULL, NULL)
INSERT [dbo].[song] ([id], [download_premit], [share_links], [title], [albumid], [imageid]) VALUES (2, 0, N'sao tu nhien lai duoc', N'ky la ghe ', NULL, NULL)
INSERT [dbo].[song] ([id], [download_premit], [share_links], [title], [albumid], [imageid]) VALUES (3, 1, N'link down o day', N'Sorry Sorry', NULL, NULL)
SET IDENTITY_INSERT [dbo].[song] OFF
GO
SET IDENTITY_INSERT [dbo].[user_type] ON 

INSERT [dbo].[user_type] ([id], [user_position]) VALUES (1, N'USER')
INSERT [dbo].[user_type] ([id], [user_position]) VALUES (2, N'ADMIN')
SET IDENTITY_INSERT [dbo].[user_type] OFF
GO
SET IDENTITY_INSERT [dbo].[users] ON 

INSERT [dbo].[users] ([id], [activity_status], [birth_day], [email], [first_name], [gender], [last_name], [password], [user_typeid], [user_name]) VALUES (1, 1, CAST(N'1999-04-27T00:00:00.0000000' AS DateTime2), N'vytran@gmai.com', N'Vy', N'Nam', N'Tran', N'$2y$12$J1J/88CRdDJxkTmZw9MyQuwp/bBW2S1vI1QYec9mYFXtS7JoReAFW
', 2, N'vytran')
INSERT [dbo].[users] ([id], [activity_status], [birth_day], [email], [first_name], [gender], [last_name], [password], [user_typeid], [user_name]) VALUES (11, 0, CAST(N'2011-10-27T07:00:00.0000000' AS DateTime2), N'thienan@gmail.com', N'Thien', N'nam', N'An', N'$2a$10$UVtQywJCO6OLHWosD3dqQeDOKY5ykMEmOaZP/YjEShdrqPFUcGF9u', 1, NULL)
INSERT [dbo].[users] ([id], [activity_status], [birth_day], [email], [first_name], [gender], [last_name], [password], [user_typeid], [user_name]) VALUES (12, 1, CAST(N'2011-10-27T07:00:00.0000000' AS DateTime2), N'thienan@gmail.com', N'Chau', N'nam', N'Minh', N'123456', 1, N'minhchau')
INSERT [dbo].[users] ([id], [activity_status], [birth_day], [email], [first_name], [gender], [last_name], [password], [user_typeid], [user_name]) VALUES (13, 1, CAST(N'2000-10-27T07:00:00.0000000' AS DateTime2), N'thienan@gmail.com', N'Thong', N'nam', N'Nhut', N'$2a$10$DccQWoX7pv8MC1kM0eaEwukc7yoQUcPfWwxkjE91GQ5YpkRU355UW', 1, N'nhutthong')
SET IDENTITY_INSERT [dbo].[users] OFF
GO
ALTER TABLE [dbo].[albums]  WITH CHECK ADD  CONSTRAINT [FK1221o5kofai7wldri05e2p3gw] FOREIGN KEY([imageid])
REFERENCES [dbo].[image] ([id])
GO
ALTER TABLE [dbo].[albums] CHECK CONSTRAINT [FK1221o5kofai7wldri05e2p3gw]
GO
ALTER TABLE [dbo].[albums]  WITH CHECK ADD  CONSTRAINT [FKaejkgcm5499582820e5gl0ih1] FOREIGN KEY([generid])
REFERENCES [dbo].[genre] ([id])
GO
ALTER TABLE [dbo].[albums] CHECK CONSTRAINT [FKaejkgcm5499582820e5gl0ih1]
GO
ALTER TABLE [dbo].[artist]  WITH CHECK ADD  CONSTRAINT [FKbisl473wkbkg4hytns8kwq735] FOREIGN KEY([imageid])
REFERENCES [dbo].[image] ([id])
GO
ALTER TABLE [dbo].[artist] CHECK CONSTRAINT [FKbisl473wkbkg4hytns8kwq735]
GO
ALTER TABLE [dbo].[comment]  WITH CHECK ADD  CONSTRAINT [FKek6auiq8xrm0656oem9d6sotx] FOREIGN KEY([userid])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[comment] CHECK CONSTRAINT [FKek6auiq8xrm0656oem9d6sotx]
GO
ALTER TABLE [dbo].[comment]  WITH CHECK ADD  CONSTRAINT [FKid2ptieobpt8loyqsg3288spb] FOREIGN KEY([songid])
REFERENCES [dbo].[song] ([id])
GO
ALTER TABLE [dbo].[comment] CHECK CONSTRAINT [FKid2ptieobpt8loyqsg3288spb]
GO
ALTER TABLE [dbo].[logs]  WITH CHECK ADD  CONSTRAINT [FKgqy8beil5y4almtq1tiyofije] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[logs] CHECK CONSTRAINT [FKgqy8beil5y4almtq1tiyofije]
GO
ALTER TABLE [dbo].[playlist]  WITH CHECK ADD  CONSTRAINT [FK5k8jjjwy046r73ri4qxt552sm] FOREIGN KEY([userid])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[playlist] CHECK CONSTRAINT [FK5k8jjjwy046r73ri4qxt552sm]
GO
ALTER TABLE [dbo].[playlist]  WITH CHECK ADD  CONSTRAINT [FKigvqyngvk07uiy4wynwpcdth7] FOREIGN KEY([songid])
REFERENCES [dbo].[song] ([id])
GO
ALTER TABLE [dbo].[playlist] CHECK CONSTRAINT [FKigvqyngvk07uiy4wynwpcdth7]
GO
ALTER TABLE [dbo].[song]  WITH CHECK ADD  CONSTRAINT [FK4xanin0sqkrp75ommo3ae2kxd] FOREIGN KEY([albumid])
REFERENCES [dbo].[albums] ([id])
GO
ALTER TABLE [dbo].[song] CHECK CONSTRAINT [FK4xanin0sqkrp75ommo3ae2kxd]
GO
ALTER TABLE [dbo].[song]  WITH CHECK ADD  CONSTRAINT [FKjs9ted2oyma4wtaj9m5wkq7ay] FOREIGN KEY([imageid])
REFERENCES [dbo].[image] ([id])
GO
ALTER TABLE [dbo].[song] CHECK CONSTRAINT [FKjs9ted2oyma4wtaj9m5wkq7ay]
GO
ALTER TABLE [dbo].[sung_by]  WITH CHECK ADD  CONSTRAINT [FK2phhhpx6phg8gkh75a8sxi8dy] FOREIGN KEY([songid])
REFERENCES [dbo].[song] ([id])
GO
ALTER TABLE [dbo].[sung_by] CHECK CONSTRAINT [FK2phhhpx6phg8gkh75a8sxi8dy]
GO
ALTER TABLE [dbo].[sung_by]  WITH CHECK ADD  CONSTRAINT [FK3p5ew27s5iaq7g4ls1hv9r0ac] FOREIGN KEY([artistid])
REFERENCES [dbo].[artist] ([id])
GO
ALTER TABLE [dbo].[sung_by] CHECK CONSTRAINT [FK3p5ew27s5iaq7g4ls1hv9r0ac]
GO
ALTER TABLE [dbo].[sung_by]  WITH CHECK ADD  CONSTRAINT [FKfawi3peaesyladhgpxrn7m92f] FOREIGN KEY([albumid])
REFERENCES [dbo].[song] ([id])
GO
ALTER TABLE [dbo].[sung_by] CHECK CONSTRAINT [FKfawi3peaesyladhgpxrn7m92f]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FKs56btya000gpydk446xrw2jx3] FOREIGN KEY([user_typeid])
REFERENCES [dbo].[user_type] ([id])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FKs56btya000gpydk446xrw2jx3]
GO
