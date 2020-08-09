//package com.lieeber.imoocvideo.model;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class Banner {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public long id;
//
//    public String name;
//    public String description;
//
//    @OneToMany(fetch = FetchType.EAGER)//设置懒加载和急加载
//    @JoinColumn(name = "bannerId")
//    public List<BannerItem> itemList;
//
//}
