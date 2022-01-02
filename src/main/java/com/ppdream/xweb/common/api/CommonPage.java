//package com.ppdream.xweb.common.api;
//
//import com.github.pagehelper.PageInfo;
//import lombok.Data;
//import org.springframework.data.domain.Page;
//
//import java.util.List;
//
//
///**
// * @Author: x43125
// * @Date: 21/04/24
// */
//@Data
//public class CommonPage<T> {
//
///**
//     * 当前页码
//     */
//
//    private Integer pageNum;
//
///**
//     * 每页数量
//     */
//
//    private Integer pageSize;
//
///**
//     * 总页数
//     */
//
//    private Integer totalPage;
//
///**
//     * 总条数
//     */
//
//    private Long total;
//
///**
//     * 分页数据
//     */
//
//    private List<T> list;
//
//
///**
//     * 将PageHelper分页后的list转为分页信息
//     */
//
//    public static <T> CommonPage<T> restPage(List<T> list) {
//        CommonPage<T> result = new CommonPage<>();
//        PageInfo<T> pageInfo = new PageInfo<>(list);
//
//        result.setTotalPage(pageInfo.getPages());
//        result.setPageNum(pageInfo.getPageNum());
//        result.setPageSize(pageInfo.getPageSize());
//        result.setTotal(pageInfo.getTotal());
//        result.setList(pageInfo.getList());
//
////        BeanUtils.copyProperties(result, pageInfo);
//
//        return result;
//    }
//
//
///**
//     * 将SpringData分页后的list转为分页信息
//     */
//
//    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
//        CommonPage<T> result = new CommonPage<T>();
//        result.setTotalPage(pageInfo.getTotalPages());
//        result.setPageNum(pageInfo.getNumber());
//        result.setPageSize(pageInfo.getSize());
//        result.setTotal(pageInfo.getTotalElements());
//        result.setList(pageInfo.getContent());
////        BeanUtils.copyProperties(result, pageInfo);
//        return result;
//    }
//
//
//}
//
