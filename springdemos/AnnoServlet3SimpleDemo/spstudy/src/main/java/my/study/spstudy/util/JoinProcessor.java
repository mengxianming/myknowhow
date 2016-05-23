package my.study.spstudy.util;
/**
 * 处理两个List连接结果的接口。
 * 具体的实现类参考工程类JoinProcessorFactory。
 * @author admin
 *
 * @param <E1> 
 * @param <E2>
 * @see JoinProcessorFactory
 */
public interface JoinProcessor<E1, E2> {
	/**
	 * 对符合连接条件的左表列(元素)与右表列(元素)进行连接处理。
	 * 
	 * @param left
	 * @param right
	 * @throws Exception
	 */
	void doJoin(E1 left, E2 right) throws Exception;
}
