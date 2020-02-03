package com.young.proto.example;


import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.young.proto.model.Proto3Super;
import com.young.proto.model.Proto3Super.RequestPage;
import com.young.proto.model.Proto3Super.TYPE;
import com.young.proto.model.extend.Proto3Extend;
import com.young.proto.model.extend.Proto3Extend.OtherObject;

public class CommonExample {

  public static void main(String[] args) throws InvalidProtocolBufferException {
    analyze(output());
  }

  /**
   * 构造RequestPage proto对象并转换为二进制流
   *
   * @return RequestPage对象的二进制流
   */
  private static byte[] output() {
    Proto3Super.RequestPage.Builder builder = Proto3Super.RequestPage.newBuilder();
    /*
     * 内部定义对象的添加（List）
     */
    Proto3Super.RequestPage.InnerObject.Builder innerBuilder1 = Proto3Super.RequestPage.InnerObject
        .newBuilder();
    innerBuilder1.setId(1);
    innerBuilder1.setUnique("name1");
    Proto3Super.RequestPage.InnerObject.Builder innerBuilder2 = Proto3Super.RequestPage.InnerObject
        .newBuilder();
    innerBuilder2.setId(2);
    innerBuilder2.setUnique("name2");
    /*
     * repeated 的list添加
     */
    builder.addInner(innerBuilder1.build());
    builder.addInner(innerBuilder2.build());

    //builder.setInner(0,innerBuilder1.build()); 会造成数组越界


    /*
     * 枚举添加
     */
    builder.setType(TYPE.TYPE_LIKE_THREE);
    /*
     * 废弃属性
     */
    builder.setDeprecatedField(Boolean.FALSE);

    /*
     * 外部import对象的添加
     */

    Proto3Extend.OtherObject.Builder otherBuilder = Proto3Extend.OtherObject.newBuilder();
    otherBuilder.setId(1);
    otherBuilder.addName("first_name");
    otherBuilder.addName("last_name");
    builder.setOther(otherBuilder.build());

    /*
     * Map的添加
     */
    builder.putTypeMap("First", TYPE.TYPE_ONE);
    builder.putTypeMap("Second", TYPE.TYPE_TWO);
    builder.putTypeMap("Third", TYPE.TYPE_THREE);
    builder.putTypeMap("Forth", TYPE.TYPE_LIKE_THREE);
    builder.putTypeMap("Third", TYPE.TYPE_ONE);//覆盖前者

    /*
     * Any属性的添加
     */
    Any any = Any.pack(otherBuilder.build());
    builder.addDetails(any);

    /*
     * oneOf属性的添加
     */
    builder.setRadioOne(1);//会被下一行覆盖字段和值
    builder.setRadioTwo(2);

    RequestPage proto = builder.build();
    return proto.toByteArray();
  }

  /**
   * 将输入的 RequestPage 二进制流转换为proto对象并逐行打印出属性
   *
   * @param output RequestPage 二进制流
   */
  private static void analyze(byte[] output) throws InvalidProtocolBufferException {
    RequestPage page = RequestPage.parseFrom(output);
    /*
     * Map中Key相同后者覆盖前者
     */
    System.out.println(page.getTypeMapMap().get("Third"));
    /*
     * oneOf属性后者会覆盖前者字段和值
     */
    System.out.println(page.getRadioOne());
    System.out.println(page.getRadioTwo());
    /*
     *Any属性的解析
     */
    System.out.println(page.getDetails(0).unpack(OtherObject.class).getId());
  }


}
