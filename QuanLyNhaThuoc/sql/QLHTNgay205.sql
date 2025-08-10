CREATE DATABASE QuanLyHieuThuoc;
GO

USE QuanLyHieuThuoc;
GO

-- Bang NhanVien
CREATE TABLE NhanVien (
    maNV NVARCHAR(30) PRIMARY KEY,
    hoTenNV NVARCHAR(50) NOT NULL,
    sdtNV VARCHAR(15) NOT NULL,
    chucVu NVARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    trangThai BIT NOT NULL,
    ngayVaoLam DATE NOT NULL,
    gioiTinh BIT NOT NULL,
    ngaySinh DATE NOT NULL,
    trinhDo NVARCHAR(50) NOT NULL
);
-- Bang LoaiKhachHang
CREATE TABLE LoaiKhachHang (
    maLoaiKhachHang NVARCHAR(30) PRIMARY KEY,
    tenLoai NVARCHAR(50) NOT NULL
);

-- Bang KhachHang
CREATE TABLE KhachHang (
    maKH NVARCHAR(30) PRIMARY KEY,
    hoTen NVARCHAR(50) NOT NULL,
    diaChi NVARCHAR(100) NOT NULL,
    sdtKH VARCHAR(15) NOT NULL,
    maloaiKhachHang NVARCHAR(30),
    gioiTinh BIT NOT NULL,
    ghiChu NVARCHAR(100),
    lichSuMuaHang NVARCHAR(MAX) NOT NULL,
    FOREIGN KEY (maloaiKhachHang) REFERENCES LoaiKhachHang(maLoaiKhachHang)
);

-- Bang NhomThuoc
CREATE TABLE NhomThuoc (
    maNhomThuoc NVARCHAR(30) PRIMARY KEY,
    tenNhomThuoc NVARCHAR(50) NOT NULL
);

-- Bang LoaiThuoc
CREATE TABLE LoaiThuoc (
    maLoaiThuoc NVARCHAR(30) PRIMARY KEY,
    tenLoai NVARCHAR(50) NOT NULL,
    manhomThuoc NVARCHAR(30),
    FOREIGN KEY (manhomThuoc) REFERENCES NhomThuoc(maNhomThuoc)
);


-- Bang DonVi
CREATE TABLE DonVi (
    maDonVi NVARCHAR(30) PRIMARY KEY,
    tenDonVi NVARCHAR(50) NOT NULL
);

-- Bang Thuoc
CREATE TABLE Thuoc (
    maThuoc NVARCHAR(30) PRIMARY KEY,
    tenThuoc NVARCHAR(50) NOT NULL,
    maLoaiThuoc NVARCHAR(30),
    maDonVi NVARCHAR(30) ,
    HSD DATE NOT NULL,
    giaBan DECIMAL(18,2) NOT NULL,
    soLuongTon INT NOT NULL,
    soLo NVARCHAR(50) NOT NULL,
--    giaNhap DECIMAL(18,2) NOT NULL,
    ngaySanXuat DATE NOT NULL,
	nhaSanXuat NVARCHAR(50) NOT NULL,
    FOREIGN KEY (maLoaiThuoc) REFERENCES LoaiThuoc(maLoaiThuoc),
    FOREIGN KEY (maDonVi) REFERENCES DonVi(maDonVi)
);

-- Bang NhaCungCap
CREATE TABLE NhaCungCap(
    maNhaCC NVARCHAR(30) PRIMARY KEY,
    tenNhaCC NVARCHAR(50) NOT NULL,
    diaChi NVARCHAR(100) NOT NULL, 
	email VARCHAR(50) NOT NULL,
	sdtNCC VARCHAR(15) NOT NULL,
	trangThai BIT NOT NULL
);

-- Bang PhieuNhapKho
CREATE TABLE PhieuNhapKho (
    maPhieuNhap NVARCHAR(30) PRIMARY KEY,
    ngayNhap DATE NOT NULL,
	maNhaCC NVARCHAR(30), 
    maNhanVien NVARCHAR(30),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNV),
	FOREIGN KEY (maNhaCC) REFERENCES NhaCungCap(maNhaCC)
);

-- Bang ChiTietPhieuNhap
CREATE TABLE ChiTietPhieuNhap (
    maPhieuNhapKho NVARCHAR(30),
    maThuoc NVARCHAR(30) ,
    soLuong INT NOT NULL,
	giaNhap DECIMAL(18,2) NOT NULL,
    ghiChu NVARCHAR(100),
    PRIMARY KEY (maPhieuNhapKho, maThuoc),
    FOREIGN KEY (maPhieuNhapKho) REFERENCES PhieuNhapKho(maPhieuNhap),
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc)
);
-- Bang PhieuXuatKho
CREATE TABLE PhieuXuatKho (
    maPhieuXuat NVARCHAR(30) PRIMARY KEY,
    ngayXuat DATE NOT NULL,
    maNhanVien NVARCHAR(30),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNV)
);

-- Bang ChiTietPhieuXuat
CREATE TABLE ChiTietPhieuXuat (
    maPhieuXuatKho NVARCHAR(30),
    maThuoc NVARCHAR(30),
    soLuong INT NOT NULL,
    ghiChu NVARCHAR(100),
    PRIMARY KEY (maPhieuXuatKho, maThuoc),
    FOREIGN KEY (maPhieuXuatKho) REFERENCES PhieuXuatKho(maPhieuXuat),
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc)
);
-- Bang KhuyenMai
CREATE TABLE KhuyenMai (
    maKhuyenMai NVARCHAR(30) PRIMARY KEY,
    tenKhuyenMai NVARCHAR(50) NOT NULL,
    ngayBatDau DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    noiDungKhuyenMai NVARCHAR(MAX) NOT NULL,
    trangThai BIT NOT NULL,
	giaTriKhuyenMai float NOT NULL
);

-- Bang HoaDon
CREATE TABLE HoaDon (
    maHoaDon NVARCHAR(30) PRIMARY KEY,
    maKhachHang NVARCHAR(30),
    maNhanVien NVARCHAR(30),
    ngayLap DATE NOT NULL,
    maKhuyenMai NVARCHAR(30),
    tienKhachTra DECIMAL(18,2) NOT NULL ,
    thoiGianTao DATETIME NOT NULL,
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKH),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maKhuyenMai) REFERENCES KhuyenMai(maKhuyenMai)
);

-- Bang ChiTietHoaDon
CREATE TABLE ChiTietHoaDon (
    maHoaDon NVARCHAR(30),
    maThuoc NVARCHAR(30),
    soLuong INT NOT NULL,
    ghiChu NVARCHAR(100),
    PRIMARY KEY (maHoaDon, maThuoc),
    FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc)
);

-- Bang TaiKhoan
CREATE TABLE TaiKhoan (
    tenDN NVARCHAR(30) PRIMARY KEY,
    matKhau NVARCHAR(50) NOT NULL,
	maNV  NVARCHAR(30) NOT NULL,
	FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
);

-- Thêm dữ liệu cho bảng NhanVien
INSERT INTO NhanVien (maNV, hoTenNV, sdtNV, chucVu, email, trangThai, ngayVaoLam, gioiTinh, ngaySinh, trinhDo)
VALUES 
('NV99001', N'Ngô Văn Ninh', '0912345678', N'Nhân viên bán hàng', 'nvn@gmail.com', 1, '2020-01-15', 1, '1999-05-20', N'Đại học'),
('NV95001', N'Huỳnh Tâm Đoan', '0923456789', N'Nhân viên bán hàng', 'htd@gmail.com', 1, '2021-03-10', 1, '1995-08-12', N'Đại học'),
('NV98001', N'Đỗ Đức Hoàn Châu', '0934567890', N'Nhân viên quản lý', 'ddhc@gmail.com', 1, '2019-11-05', 0, '1998-12-30', N'Đại học'),
('NV98002', N'Lâm Phát Đạt', '0956789012', N'Nhân viên bán hàng', 'lpd@gmail.com', 1, '2023-01-10', 1, '1998-04-15', N'Đại học');


-- Thêm dữ liệu cho bảng LoaiKhachHang
INSERT INTO LoaiKhachHang (maLoaiKhachHang, tenLoai)
VALUES 
('LKH001', N'Thành viên bạc'),
('LKH002', N'Thành viên vàng'),
('LKH003', N'Thành viên kim cương'),
('LKH004', N'Khách vãng lai');

-- Thêm dữ liệu cho bảng KhachHang
INSERT INTO KhachHang (maKH, hoTen, diaChi, sdtKH, maloaiKhachHang, gioiTinh, ghiChu, lichSuMuaHang)
VALUES 
('KH2024102000001', N'Nguyễn Thị Minh', N'123 Đường ABC, Quận 1', '0911111111', 'LKH002', 0, N'Khách hàng thân thiết', N'Mua thường xuyên'),
('KH2024112000001', N'Trần Văn Giang', N'456 Đường XYZ, Quận 2', '0922222222', 'LKH004', 1, N'Khách mới', N'Mua lần đầu'),
('KH2025022400001', N'Lê Thị Hồng', N'789 Đường DEF, Quận 3', '0933333333', 'LKH003', 0, N'Khách VIP', N'Mua số lượng lớn'),
('KH2025031300001', N'Phạm Văn Sang', N'321 Đường GHI, Quận 4', '0944444444', 'LKH004', 1, 'Khách mới', N'Mua không thường xuyên'),
('KH2025041300001', N'Hoàng Thị Kim', N'654 Đường KLM, Quận 5', '0955555555', 'LKH003', 0, N'Khách đặc biệt', N'Mua số lượng lớn'),
('KH2025050100001', N'Vũ Đình Khôi', N'12 Đường Lê Lợi, Quận 1', '0966666666', 'LKH001', 1, N'Khách thường xuyên', N'Mua thường xuyên'),
('KH2025050200001', N'Đặng Thị Ngọc', N'34 Đường Nguyễn Huệ, Quận 1', '0977777777', 'LKH002', 0, N'Khách VIP', N'Mua số lượng lớn'),
('KH2025050300001', N'Bùi Văn Thành', N'56 Đường Hai Bà Trưng, Quận 3', '0988888888', 'LKH004', 1, N'Khách mới', N'Mua lần đầu'),
('KH2025050400001', N'Lý Thị Hoa', N'78 Đường Cách Mạng Tháng 8, Quận 10', '0999999999', 'LKH003', 0, N'Khách đặc biệt', N'Mua thường xuyên'),
('KH2025050500001', N'Mai Văn Đạt', N'90 Đường 3/2, Quận 11', '0900000000', 'LKH001', 1, N'Khách thân thiết', N'Mua thường xuyên');
-- Thêm dữ liệu cho bảng NhomThuoc
INSERT INTO NhomThuoc (maNhomThuoc, tenNhomThuoc)
VALUES 
('NKS', N'Kháng sinh'),
('NGD', N'Giảm đau'),
('NVM', N'Vitamin'),
('NTH', N'Tiêu hóa'),
('NDL', N'Da liễu');

-- Thêm dữ liệu cho bảng LoaiThuoc
INSERT INTO LoaiThuoc (maLoaiThuoc, tenLoai, manhomThuoc)
VALUES 
('LNKS00001', N'Kháng sinh phổ rộng', 'NKS'),
('LNGD00001', N'Giảm đau thần kinh', 'NGD'),
('LNVM00001', N'Vitamin tổng hợp', 'NVM'),
('LNTH00001', N'Thuốc trị đau dạ dày', 'NTH'),
('LNDL00001', N'Thuốc trị nấm da', 'NDL');

-- Thêm dữ liệu cho bảng DonVi
INSERT INTO DonVi (maDonVi, tenDonVi)
VALUES 
('DV001', N'Viên'),
('DV002', N'Hộp'),
('DV003', N'Chai'),
('DV004', N'Tuýp'),
('DV005', N'Gói');


-- Thêm dữ liệu cho bảng Thuoc
INSERT INTO Thuoc (maThuoc, tenThuoc, maLoaiThuoc, maDonVi, HSD, giaBan, soLuongTon, soLo, ngaySanXuat, nhaSanXuat)
VALUES 
('MTKS0001', 'Amoxicillin 500mg', 'LNKS00001', 'DV001', '2025-12-31', 5000, 100, 'LO123','2022-01-01', N'Công ty TNHH United Pharma'),
('MTGD0001', 'Paracetamol 500mg', 'LNGD00001', 'DV001', '2025-06-30', 20000, 50, 'LO456', '2022-02-15' ,N'Công ty CP Xuất nhập khẩu Y tế Domesco'),
('MTVM0001', 'Vitamin C 1000mg', 'LNVM00001', 'DV002', '2026-03-31', 150000, 30, 'LO789', '2022-03-10', N'Công ty TNHH Dược phẩm Shinpoong Daewoo'),
('MTTH0001', 'Gastropulgite', 'LNTH00001', 'DV002', '2025-09-30', 80000, 40, 'LO101', '2022-04-05', N'Công ty Cổ phần Pymepharco'),
('MTDL0001', 'Ketoconazole 2%', 'LNDL00001', 'DV004', '2025-12-31', 120000, 25, 'LO112', '2022-05-20', N'Công ty TNHH Dược phẩm Shinpoong Daewoo'),

('MTKS0002', 'Cephalexin 500mg', 'LNKS00001', 'DV001', '2026-05-31', 8000, 80, 'LO234', '2023-02-15', N'Công ty TNHH United Pharma'),
('MTGD0002', 'Ibuprofen 400mg', 'LNGD00001', 'DV001', '2026-08-31', 25000, 60, 'LO567', '2023-03-20', N'Công ty CP Xuất nhập khẩu Y tế Domesco'),
('MTVM0002', 'Vitamin D3 2000IU', 'LNVM00001', 'DV002', '2027-01-31', 180000, 40, 'LO890', '2023-04-10', N'Công ty TNHH Dược phẩm Shinpoong Daewoo'),
('MTTH0002', 'Omeprazole 20mg', 'LNTH00001', 'DV001', '2026-11-30', 60000, 70, 'LO123', '2023-05-15', N'Công ty Cổ phần Pymepharco'),
('MTDL0002', 'Clotrimazole 1%', 'LNDL00001', 'DV004', '2026-10-31', 90000, 35, 'LO456', '2023-06-20', N'Công ty TNHH Dược phẩm Shinpoong Daewoo'),

('MTKS0003', 'Azithromycin 250mg', 'LNKS00001', 'DV001', '2026-12-31', 12000, 150, 'LO345', '2021-11-01', N'Công ty TNHH United Pharma'),
('MTGD0003', 'Aspirin 500mg', 'LNGD00001', 'DV001', '2026-10-31', 15000, 120, 'LO678', '2021-12-15', N'Công ty CP Xuất nhập khẩu Y tế Domesco'),
('MTVM0003', 'Vitamin B Complex', 'LNVM00001', 'DV002', '2027-05-31', 200000, 80, 'LO901', '2022-01-20', N'Công ty TNHH Dược phẩm Shinpoong Daewoo'),
('MTTH0003', 'Domperidone 10mg', 'LNTH00001', 'DV001', '2026-09-30', 45000, 90, 'LO234', '2022-02-10', N'Công ty Cổ phần Pymepharco'),
('MTDL0003', 'Miconazole 2%', 'LNDL00001', 'DV004', '2026-11-30', 110000, 60, 'LO567', '2022-03-05', N'Công ty TNHH Dược phẩm Shinpoong Daewoo'),

-- Thuốc sắp hết hạn (HSD gần)
('MTKS0004', 'Ciprofloxacin 500mg', 'LNKS00001', 'DV001', '2024-08-31', 15000, 30, 'LO890', '2023-01-15', N'Công ty TNHH United Pharma'),
('MTGD0004', 'Diclofenac 50mg', 'LNGD00001', 'DV001', '2024-09-30', 30000, 25, 'LO123', '2023-02-20', N'Công ty CP Xuất nhập khẩu Y tế Domesco'),
('MTVM0004', 'Vitamin E 400IU', 'LNVM00001', 'DV002', '2024-10-31', 120000, 15, 'LO456', '2023-03-10', N'Công ty TNHH Dược phẩm Shinpoong Daewoo'),
('MTTH0004', 'Lansoprazole 30mg', 'LNTH00001', 'DV001', '2024-07-31', 70000, 20, 'LO789', '2023-04-05', N'Công ty Cổ phần Pymepharco'),
('MTDL0004', 'Terbinafine 1%', 'LNDL00001', 'DV004', '2024-11-30', 130000, 18, 'LO012', '2023-05-15', N'Công ty TNHH Dược phẩm Shinpoong Daewoo'),

-- Thuốc bán chạy (số lượng tồn ít, nhập gần đây)
('MTKS0005', 'Amoxicillin + Clavulanate 625mg', 'LNKS00001', 'DV001', '2026-06-30', 25000, 15, 'LO345', '2024-01-10', N'Công ty TNHH United Pharma'),
('MTGD0005', 'Celecoxib 200mg', 'LNGD00001', 'DV001', '2026-07-31', 40000, 10, 'LO678', '2024-02-15', N'Công ty CP Xuất nhập khẩu Y tế Domesco'),
('MTVM0005', 'Multivitamin tổng hợp', 'LNVM00001', 'DV002', '2026-09-30', 250000, 8, 'LO901', '2024-03-20', N'Công ty TNHH Dược phẩm Shinpoong Daewoo'),
('MTTH0005', 'Rabeprazole 20mg', 'LNTH00001', 'DV001', '2026-08-31', 90000, 12, 'LO234','2024-04-10', N'Công ty Cổ phần Pymepharco'),
('MTDL0005', 'Fluconazole 150mg', 'LNDL00001', 'DV001', '2026-10-31', 80000, 5, 'LO567', '2024-05-05', N'Công ty TNHH Dược phẩm Shinpoong Daewoo');

-- Thêm dữ liệu cho bảng NhaCungCap 
INSERT INTO NhaCungCap (maNhaCC, tenNhaCC, diaChi, email, sdtNCC, trangThai)
VALUES 
('NCC001', N'Công ty Dược phẩm A', N'111 Đường Nhà Cung Cấp, Quận 1', 'ncc1@gmail.com', '0911111111', 1),
('NCC002', N'Công ty Dược phẩm B', N'222 Đường Nhà Cung Cấp, Quận 2', 'ncc2@gmail.com', '0922222222', 1),
('NCC003', N'Công ty Dược phẩm C', N'333 Đường Nhà Cung Cấp, Quận 3', 'ncc3@gmail.com', '0933333333', 0),
('NCC004', N'Công ty Dược phẩm D', N'444 Đường Nhà Cung Cấp, Quận 4', 'ncc4@gmail.com', '0944444444', 1),
('NCC005', N'Công ty Dược phẩm E', N'555 Đường Nhà Cung Cấp, Quận 5', 'ncc5@gmail.com', '0955555555', 1);

-- Thêm dữ liệu cho bảng PhieuNhapKho
INSERT INTO PhieuNhapKho (maPhieuNhap, ngayNhap, maNhaCC, maNhanVien)
VALUES 
('PN10012301001', '2023-01-10', 'NCC001', 'NV98001'),
('PN15122301001', '2023-12-15', 'NCC002', 'NV98001'),
('PN20032401001', '2024-03-20', 'NCC003', 'NV98001'),
('PN25102401001', '2024-10-25', 'NCC004', 'NV98001'),
('PN30032501001', '2025-03-30', 'NCC005', 'NV98001'),
('PN15052501001', '2025-05-15', 'NCC001', 'NV98002'),
('PN20052501001', '2025-05-20', 'NCC002', 'NV95001'),
('PN25052501001', '2025-05-25', 'NCC003', 'NV95001'),
('PN30052501001', '2025-05-30', 'NCC004', 'NV95001'),
('PN05062501001', '2025-06-05', 'NCC005', 'NV98002'),
('PN10012301002', '2023-01-20', 'NCC001', 'NV98002'),
('PN15122301002', '2023-12-20', 'NCC002', 'NV98001'),
('PN20032401002', '2024-03-25', 'NCC003', 'NV98001'),
('PN25102401002', '2024-10-30', 'NCC004', 'NV98002'),
('PN30032501002', '2025-04-05', 'NCC005', 'NV98001'),
('PN15052501002', '2025-05-20', 'NCC001', 'NV98001'),
('PN20052501002', '2025-05-25', 'NCC002', 'NV98002'),
('PN25052501002', '2025-05-30', 'NCC003', 'NV98001'),
('PN30052501002', '2025-06-05', 'NCC004', 'NV98002'),
('PN05062501002', '2025-06-10', 'NCC005', 'NV98002');

-- Thêm dữ liệu cho bảng ChiTietPhieuNhap
INSERT INTO ChiTietPhieuNhap (maPhieuNhapKho, maThuoc, soLuong, giaNhap, ghiChu)
VALUES 
('PN10012301001', 'MTKS0001', 200, 3000, N'Nhập đợt 1'),
('PN10012301001', 'MTGD0001', 100, 15000 ,N'Nhập đợt 1'),
('PN15122301001', 'MTVM0001', 50, 12000,N'Nhập đợt 2'),
('PN20032401001', 'MTVM0001', 80, 60000 ,N'Nhập đợt 3'),
('PN25102401001', 'MTDL0001', 60, 90000 ,N'Nhập đợt 4'),

-- Thuốc bán chạy (số lượng tồn ít, nhập gần đây)
('PN15052501001', 'MTKS0002', 150, 5000,N'Nhập đợt 2'),
('PN20052501001', 'MTGD0002', 120, 18000, N'Nhập đợt 3'),
('PN25052501001', 'MTVM0002', 60,  140000, N'Nhập đợt 1'),
('PN30052501001', 'MTTH0002', 90, 40000,N'Nhập đợt 6'),
('PN05062501001', 'MTDL0002', 50, 65000,N'Nhập Nhập đợt 3'),
-- Nhập thuốc tồn kho lâu
('PN10012301002', 'MTKS0003', 200, 8000, N'Nhập đợt 1'),
('PN10012301002', 'MTGD0003', 150, 10000,N'Nhập đợt 1'),
('PN15122301002', 'MTVM0003', 100, 150000, N'Nhập đợt 2'),
('PN20032401002', 'MTTH0003', 120, 30000,N'Nhập đợt 3'),
('PN25102401002', 'MTDL0003', 80,  80000, N'Nhập đợt 4'),

-- Nhập thuốc sắp hết hạn
('PN30032501002', 'MTKS0004', 50,  10000,N'Nhập đợt 1'),
('PN15052501002', 'MTGD0004', 40,20000,N'Nhập đợt 2'),
('PN20052501002', 'MTVM0004', 30,90000,  N'Nhập đợt 3'),
('PN25052501002', 'MTTH0004', 35,50000, N'Nhập đợt 4'),
('PN30052501002', 'MTDL0004', 25, 100000,N'Nhập đợt 5'),

-- Nhập thuốc bán chạy
('PN05062501002', 'MTKS0005', 30,18000, N'Nhập đợt 6'),
('PN05062501002', 'MTGD0005', 20,30000, N'Nhập đợt 6'),
('PN05062501002', 'MTVM0005', 15,  200000, N'Nhập đợt 6'),
('PN05062501002', 'MTTH0005', 25,70000, N'Nhập đợt 6'),
('PN05062501002', 'MTDL0005', 10, 60000,N'Nhập đợt 6');

-- Thêm dữ liệu cho bảng PhieuXuatKho
INSERT INTO PhieuXuatKho (maPhieuXuat, ngayXuat, maNhanVien)
VALUES 
('PX15012301001', '2023-01-15', 'NV99001'),
('PX20122301001', '2023-12-20', 'NV99001'),
('PX25032401001', '2024-03-25', 'NV99001'),
('PX30102401001', '2024-10-30', 'NV99001'),
('PX05042501001', '2023-04-05', 'NV99001'),
('PX10052501001', '2025-05-10', 'NV95001'),
('PX15052501001', '2025-05-15', 'NV95001'),
('PX20052501001', '2025-05-20', 'NV95001'),
('PX25052501001', '2025-05-25', 'NV95001'),
('PX30052501001', '2025-05-30', 'NV95001'), 
('PX20012301001', '2023-01-20', 'NV95001'),
('PX25122301001', '2023-12-25', 'NV98001'),
('PX30032401001', '2024-03-30', 'NV98001'),
('PX01112401001', '2024-11-01', 'NV98002'),
('PX10042501001', '2023-04-10', 'NV98002'),
('PX20042501001', '2025-05-20', 'NV98002'),
('PX25053501001', '2025-05-25', 'NV98002'),
('PX30052501002', '2025-05-30', 'NV98002'), 
('PX05062501001', '2025-06-05', 'NV98002'),
('PX10062501001', '2025-06-10', 'NV98002');

-- Thêm dữ liệu cho bảng ChiTietPhieuXuat
INSERT INTO ChiTietPhieuXuat (maPhieuXuatKho, maThuoc, soLuong, ghiChu)
VALUES 
('PX15012301001', 'MTKS0001', 50, N'Xuất bán'),
('PX15012301001', 'MTGD0001', 30, N'Xuất bán'),
('PX20122301001', 'MTVM0001', 20, N'Xuất bán'),
('PX25032401001', 'MTVM0001', 40, N'Xuất bán'),
('PX30102401001', 'MTDL0001', 25, N'Xuất bán'),
('PX10052501001', 'MTKS0002', 30, N'Xuất bán lẻ'),
('PX15052501001', 'MTGD0002', 25, N'Xuất theo đơn'),
('PX20052501001', 'MTVM0002', 15, N'Xuất cho chi nhánh'),
('PX25052501001', 'MTTH0002', 20, N'Xuất bán lẻ'),
('PX30052501001', 'MTDL0002', 10, N'Xuất theo yêu cầu');

-- Thêm dữ liệu cho bảng KhuyenMai
INSERT INTO KhuyenMai (maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, noiDungKhuyenMai, trangThai, giaTriKhuyenMai)
VALUES 
('KM0001', N'Khuyến mãi mùa hè', '2024-06-01', '2024-06-30', N'Giảm giá 10% cho tất cả sản phẩm', 0, 10),
('KM0002', N'Khuyến mãi thành viên', '2024-07-01', '2024-07-31', N'Giảm giá 15% cho thành viên vàng', 0, 15),
('KM0003', N'Khuyến mãi cuối năm', '2024-12-01', '2024-12-31', N'Giảm giá 20% cho đơn hàng trên 1 triệu', 0, 20),
('KM0004', N'Khuyến mãi sinh nhật', '2025-03-01', '2023-03-31', N'Giảm giá 5% cho khách hàng có sinh nhật trong tháng', 0, 5),
('KM0005', N'Khuyến mãi đặc biệt', '2025-04-01', '2023-04-30', N'Mua 2 tặng 1 cho sản phẩm vitamin', 1, 50);



-- Thêm dữ liệu cho bảng HoaDon
INSERT INTO HoaDon (maHoaDon, maKhachHang, maNhanVien, ngayLap, maKhuyenMai, tienKhachTra, thoiGianTao)
VALUES 
('HD2024112001001', 'KH2024102000001', 'NV99001', '2024-11-20', NULL, 450000, '2024-11-20 09:30:00'),
('HD2024112002001', 'KH2024112000001', 'NV99001', '2024-11-20', NULL, 680000, '2024-11-20 14:15:00'),
('HD2025032601001', 'KH2025022400001', 'NV95001', '2025-03-26', 'KM0004', 1200000, '2025-03-26 10:45:00'),
('HD2025031402001', 'KH2025031300001', 'NV95001', '2025-03-13', 'KM0004', 320000, '2025-03-13 16:20:00'),
('HD2025043001001', 'KH2025041300001', 'NV99001', '2025-04-30', 'KM0005', 850000, '2025-04-30 11:10:00'),
('HD2025050101001', 'KH2025050100001', 'NV98001', '2025-05-01', NULL, 350000, '2025-05-01 10:15:00'),
('HD2025050201001', 'KH2025050200001', 'NV98002', '2025-05-02', 'KM0005', 950000, '2025-05-02 14:30:00'),
('HD2025050301001', 'KH2025050300001', 'NV98002', '2025-05-03', NULL, 420000, '2025-05-03 09:45:00'),
('HD2025050401001', 'KH2025050400001', 'NV98001', '2025-05-04', 'KM0003', 1500000, '2025-05-04 16:20:00'),
('HD2025050501001', 'KH2025050500001', 'NV98002', '2025-05-05', NULL, 280000, '2025-05-05 11:10:00'),
('HD2025050601001', 'KH2025050100001', 'NV99001', '2025-05-06', NULL, 280000, '2025-05-06 09:15:00'),
('HD2025050701001', 'KH2025050200001', 'NV95001', '2025-05-07', 'KM0005', 750000, '2025-05-07 14:30:00'),
('HD2025050801001', 'KH2025050300001', 'NV99001', '2025-05-08', NULL, 320000, '2025-05-08 10:45:00'),
('HD2025050901001', 'KH2025050400001', 'NV95001', '2025-05-09', 'KM0003', 1100000, '2025-05-09 16:20:00'),
('HD2025051001001', 'KH2025050500001', 'NV99001', '2025-05-10', NULL, 190000, '2025-05-10 11:10:00'),
('HD2025051101001', 'KH2024102000001', 'NV95001', '2025-05-11', NULL, 420000, '2025-05-11 09:30:00'),
('HD2025051201001', 'KH2024112000001', 'NV99001', '2025-05-12', 'KM0005', 680000, '2025-05-12 14:15:00'),
('HD2025051301001', 'KH2025022400001', 'NV95001', '2025-05-13', NULL, 950000, '2025-05-13 10:45:00'),
('HD2025051401001', 'KH2025031300001', 'NV99001', '2025-05-14', 'KM0003', 350000, '2025-05-14 16:20:00'),
('HD2025051501001', 'KH2025041300001', 'NV95001', '2025-05-15', NULL, 520000, '2025-05-15 11:10:00');

-- Thêm dữ liệu cho bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maHoaDon, maThuoc, soLuong, ghiChu)
VALUES 
('HD2024112001001', 'MTKS0001', 10, N'Kê đơn'),
('HD2024112001001', 'MTGD0001', 5, N'Kê đơn'),
('HD2024112002001', 'MTVM0001', 2, N'Khách Tự mua'),
('HD2025032601001', 'MTTH0001', 8, N'Theo đơn thuốc'),
('HD2025031402001', 'MTDL0001', 3, N'Khách Tự mua'),
('HD2025050101001', 'MTKS0002', 5, N'Kê đơn bác sĩ'),
('HD2025050201001', 'MTVM0002', 3, N'Khách Tự mua'),
('HD2025050301001', 'MTGD0002', 8, N'Theo toa thuốc'),
('HD2025050401001', 'MTTH0002', 10, N'Theo đơn thuốc'),
('HD2025050501001', 'MTDL0002', 4, N'Mua tự nguyện'),
-- Hóa đơn cho thuốc bán chạy
('HD2025050601001', 'MTKS0005', 3, N'Kê đơn bác sĩ'),
('HD2025050701001', 'MTVM0005', 2, N'Mua tự nguyện'),
('HD2025050801001', 'MTGD0005', 4, N'Theo toa thuốc'),
('HD2025050901001', 'MTTH0005', 5, N'Mua số lượng lớn'),
('HD2025051001001', 'MTDL0005', 2, N'Mua tự nguyện'),

-- Hóa đơn cho thuốc sắp hết hạn
('HD2025051101001', 'MTKS0004', 5, N'Kê đơn bác sĩ'),
('HD2025051201001', 'MTVM0004', 1, N'Mua tự nguyện'),
('HD2025051301001', 'MTGD0004', 3, N'Theo toa thuốc'),
('HD2025051401001', 'MTTH0004', 2, N'Mua tự nguyện'),
('HD2025051501001', 'MTDL0004', 1, N'Mua tự nguyện'),

-- Hóa đơn cho thuốc tồn kho lâu
('HD2025050601001', 'MTKS0003', 1, N'Kê đơn bác sĩ'),
('HD2025050701001', 'MTVM0003', 1, N'Mua tự nguyện'),
('HD2025050801001', 'MTGD0003', 2, N'Theo toa thuốc'),
('HD2025050901001', 'MTTH0003', 1, N'Mua tự nguyện'),
('HD2025051001001', 'MTDL0003', 1, N'Mua tự nguyện');


--Khách hàng
CREATE PROCEDURE layKhachHangTuSDT
	@sdt NVARCHAR(20)
AS
BEGIN 
	SELECT 
		kh.maKH,
		kh.hoTen,
		kh.diaChi,
		lkh.tenLoai
	FROM KhachHang kh
	JOIN LoaiKhachHang lkh ON kh.maLoaiKhachHang = lkh.maLoaiKhachHang
	WHERE kh.sdtKH = @sdt
END

CREATE PROCEDURE layDanhSachKhachHang
AS
BEGIN
    SELECT 
        kh.maKH,
		kh.hoTen,
		kh.diaChi,
		kh.sdtKH,
        lkh.tenLoai,
		kh.gioiTinh,
		kh.ghiChu,
		kh.lichSuMuaHang
    FROM 
        KhachHang kh
    JOIN 
        LoaiKhachHang lkh ON kh.maloaiKhachHang = lkh.maLoaiKhachHang;
END
EXEC layDanhSachKhachHang;

----------Khuyến mãi
INSERT INTO KhuyenMai (maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, noiDungKhuyenMai, trangThai, giaTriKhuyenMai)
VALUES 
('KM0006', N'Khuyến mãi thành viên kim cương', '2024-05-03', '2024-06-28', N'Giảm giá 20% cho tất cả sản phẩm', 1, 20),
('KM0007', N'Khuyến mãi thành viên bạc', '2024-05-03', '2024-06-28', N'Giảm giá 10% cho thành viên vàng', 0, 10)


SELECT * FROM KhuyenMai WHERE trangThai = 1

SELECT TOP 1 tenKhuyenMai, giaTriKhuyenMai FROM KhuyenMai
WHERE tenKhuyenMai LIKE N'%thành viên kim cương%' AND trangThai = 1
ORDER BY giaTriKhuyenMai DESC


DROP PROCEDURE LayKhuyenMaiTheoLoaiKH;
CREATE PROCEDURE LayKhuyenMaiTheoLoaiKH
    @loaiKH NVARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;
    IF LOWER(@loaiKH) = N'thành viên kim cương'
    BEGIN
        SELECT TOP 1 tenKhuyenMai, giaTriKhuyenMai
        FROM KhuyenMai
        WHERE tenKhuyenMai LIKE N'%kim cương%'
            AND trangThai = 1
        ORDER BY giaTriKhuyenMai DESC;
    END
    ELSE IF LOWER(@loaiKH) = N'thành viên vàng'
    BEGIN
        SELECT TOP 1 tenKhuyenMai, giaTriKhuyenMai
        FROM KhuyenMai
        WHERE tenKhuyenMai LIKE N'%vàng%'
            AND trangThai = 1
        ORDER BY giaTriKhuyenMai DESC;
    END
	ELSE IF LOWER(@loaiKH) = N'thành viên bạc'
    BEGIN
        SELECT TOP 1 tenKhuyenMai, giaTriKhuyenMai
        FROM KhuyenMai
        WHERE tenKhuyenMai LIKE N'%bạc%'
            AND trangThai = 1
        ORDER BY giaTriKhuyenMai DESC;
    END
    ELSE 
    BEGIN
        SELECT TOP 1 tenKhuyenMai, giaTriKhuyenMai
        FROM KhuyenMai
        WHERE tenKhuyenMai NOT LIKE N'%thành viên%'
			AND tenKhuyenMai NOT LIKE N'%đặc biệt%'
            AND trangThai = 1
        ORDER BY giaTriKhuyenMai DESC;
    END
END

EXEC LayKhuyenMaiTheoLoaiKH N'thành viên kim cương'
