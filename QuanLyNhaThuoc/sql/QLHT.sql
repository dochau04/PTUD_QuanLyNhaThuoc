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
    giaNhap DECIMAL(18,2) NOT NULL,
    NSX DATE NOT NULL,
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
    ghiChu NVARCHAR(100),
    PRIMARY KEY (maPhieuNhapKho, maThuoc),
    FOREIGN KEY (maPhieuNhapKho) REFERENCES PhieuNhapKho(maPhieuNhap),
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc)
);

-- Bang PhieuXuatKho
CREATE TABLE PhieuXuatKho (
    maPhieuXuat NVARCHAR(30) PRIMARY KEY,
    ngayXuat DATE NOT NULL,
    maNhanVien NVARCHAR(30)
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
('KH2025041300001', N'Hoàng Thị Kim', N'654 Đường KLM, Quận 5', '0955555555', 'LKH003', 0, N'Khách đặc biệt', N'Mua số lượng lớn');
 
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
INSERT INTO Thuoc (maThuoc, tenThuoc, maLoaiThuoc, maDonVi, HSD, giaBan, soLuongTon, soLo, giaNhap, NSX, nhaSanXuat)
VALUES 
('MTKS0001', 'Amoxicillin 500mg', 'LNKS00001', 'DV001', '2025-12-31', 5000, 100, 'LO123', 3000, '2022-01-01', N'Công ty TNHH United Pharma'),
('MTGD0001', 'Paracetamol 500mg', 'LNGD00001', 'DV001', '2025-06-30', 20000, 50, 'LO456', 15000, '2022-02-15' ,N'Công ty CP Xuất nhập khẩu Y tế Domesco'),
('MTVM0001', 'Vitamin C 1000mg', 'LNVM00001', 'DV002', '2026-03-31', 150000, 30, 'LO789', 120000, '2022-03-10', N'Công ty TNHH Dược phẩm Shinpoong Daewoo'),
('MTTH0001', 'Gastropulgite', 'LNTH00001', 'DV002', '2025-09-30', 80000, 40, 'LO101', 60000, '2022-04-05', N'Công ty Cổ phần Pymepharco'),
('MTDL0001', 'Ketoconazole 2%', 'LNDL00001', 'DV004', '2025-12-31', 120000, 25, 'LO112', 90000, '2022-05-20', N'Công ty TNHH Dược phẩm Shinpoong Daewoo');

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
('PN30032501001', '2025-03-30', 'NCC005', 'NV98001');

-- Thêm dữ liệu cho bảng ChiTietPhieuNhap
INSERT INTO ChiTietPhieuNhap (maPhieuNhapKho, maThuoc, soLuong, ghiChu)
VALUES 
('PN10012301001', 'MTKS0001', 200, N'Nhập đợt 1'),
('PN10012301001', 'MTGD0001', 100, N'Nhập đợt 1'),
('PN15122301001', 'MTVM0001', 50, N'Nhập đợt 2'),
('PN20032401001', 'MTVM0001', 80, N'Nhập đợt 3'),
('PN25102401001', 'MTDL0001', 60, N'Nhập đợt 4');

-- Thêm dữ liệu cho bảng PhieuXuatKho
INSERT INTO PhieuXuatKho (maPhieuXuat, ngayXuat, maNhanVien)
VALUES 
('PX15012301001', '2023-01-15', 'NV98001'),
('PX20122301001', '2023-12-20', 'NV98001'),
('PX25032401001', '2024-03-25', 'NV98001'),
('PX30102401001', '2024-10-30', 'NV98001'),
('PX05042501001', '2023-04-05', 'NV98001');

-- Thêm dữ liệu cho bảng ChiTietPhieuXuat
INSERT INTO ChiTietPhieuXuat (maPhieuXuatKho, maThuoc, soLuong, ghiChu)
VALUES 
('PX15012301001', 'MTKS0001', 50, N'Xuất bán'),
('PX15012301001', 'MTGD0001', 30, N'Xuất bán'),
('PX20122301001', 'MTVM0001', 20, N'Xuất bán'),
('PX25032401001', 'MTVM0001', 40, N'Xuất bán'),
('PX30102401001', 'MTDL0001', 25, N'Xuất bán');

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
('HD2025043001001', 'KH2025041300001', 'NV99001', '2025-04-30', 'KM0005', 850000, '2025-04-30 11:10:00');

-- Thêm dữ liệu cho bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maHoaDon, maThuoc, soLuong, ghiChu)
VALUES 
('HD2024112001001', 'MTKS0001', 10, N'Kê đơn'),
('HD2024112001001', 'MTGD0001', 5, N'Kê đơn'),
('HD2024112002001', 'MTVM0001', 2, N'Tự mua'),
('HD2025032601001', 'MTTH0001', 8, N'Theo đơn thuốc'),
('HD2025031402001', 'MTDL0001', 3, N'Tự mua');


