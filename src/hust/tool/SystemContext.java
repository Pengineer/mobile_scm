package hust.tool;

/**
 * 为了避免每次前端传递pageSize，pageIndex，pageOffset，order，sort时，后台都的action都需要对用的属性接收，这里将这些属性
 * 抽取出来与请求线程绑定（等价于与请求绑定），然后通过struts的过滤器进行这些属性的注入。（smdb将这些属性定义在BaseAction中）
 * 
 * 注意  get，set，remove 方法与一般的实体类的不同
*/

public class SystemContext {

	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageIndex = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	/**
	 * 升序还是降序
	 */
	private static ThreadLocal<String> order = new ThreadLocal<String>();
	/**
	 * 根据那个字段排序
	 */
	private static ThreadLocal<String> sort = new ThreadLocal<String>();
	
	
	public static String getOrder() {
		String o = order.get();
		if (o == null) {
			return "0";
		}
		return o;
	}
	
	public static void setOrder(String _order) {
		order.set(_order);
	}
	
	public static void removeOrder() {
		order.remove();
	}
	
	public static String getSort() {
		String s = sort.get();  
		if (s == null) {
			return "0";
		}
		return s;
	}
	
	public static void setSort(String _sort) {
		sort.set(_sort);
	}
	
	public static void removeSort() {
		sort.remove();
	}
	
	public static int getPageOffset() {
		Integer po = pageOffset.get();  
		if (po == null) {
			return 0;
		}
		return po;
	}
	
	public static void setPageOffset(int _pageOffset) {
		pageOffset.set(_pageOffset);
	}
	
	public static void removePageOffset() {
		pageOffset.remove();
	}
	
	public static void setPageSize(int _pageSize) {
		pageSize.set(_pageSize);
	}
	
	public static int getPageSize() {
		Integer ps = pageSize.get();  
		if (ps == null) {
			return 0;
		}
		return ps;
	}
	
	public static void removePageSize() {
		pageSize.remove();
	}
	
	public static void setPageIndex(int _pageIndex) {
		pageIndex.set(_pageIndex);
	}
	
	public static int getPageIndex() {
		Integer pi = pageIndex.get();  
		if (pi == null) {
			return 0;
		}
		return pi;
	}
	
	public static void removePageIndex() {
		pageIndex.remove();
	}
}