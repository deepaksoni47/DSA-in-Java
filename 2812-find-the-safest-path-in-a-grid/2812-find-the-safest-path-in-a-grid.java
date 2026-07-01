class Solution {

    int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n=grid.size();

        int[][] dist=new int[n][n];
        for(int[] row:dist)
            Arrays.fill(row,-1);

        Queue<int[]> q=new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid.get(i).get(j)==1){
                    dist[i][j]=0;
                    q.offer(new int[]{i,j});
                }
            }
        }

        while(!q.isEmpty()){

            int[] cur=q.poll();

            for(int[] d:dirs){

                int nr=cur[0]+d[0];
                int nc=cur[1]+d[1];

                if(nr<0||nc<0||nr>=n||nc>=n) continue;
                if(dist[nr][nc]!=-1) continue;

                dist[nr][nc]=dist[cur[0]][cur[1]]+1;
                q.offer(new int[]{nr,nc});
            }
        }

        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->b[2]-a[2]);

        int[][] best=new int[n][n];

        for(int[] row:best)
            Arrays.fill(row,-1);

        pq.offer(new int[]{0,0,dist[0][0]});
        best[0][0]=dist[0][0];

        while(!pq.isEmpty()){

            int[] cur=pq.poll();

            int r=cur[0];
            int c=cur[1];
            int safe=cur[2];

            if(r==n-1&&c==n-1)
                return safe;

            if(safe<best[r][c])
                continue;

            for(int[] d:dirs){

                int nr=r+d[0];
                int nc=c+d[1];

                if(nr<0||nc<0||nr>=n||nc>=n)
                    continue;

                int ns=Math.min(safe,dist[nr][nc]);

                if(ns>best[nr][nc]){
                    best[nr][nc]=ns;
                    pq.offer(new int[]{nr,nc,ns});
                }
            }
        }

        return 0;
    }
}
//approach 2 multi source bfs + binary search
/*class Solution {

    int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n=grid.size();
        int[][] dist=new int[n][n];

        for(int[] row:dist) Arrays.fill(row,-1);

        Queue<int[]> q=new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid.get(i).get(j)==1){
                    dist[i][j]=0;
                    q.offer(new int[]{i,j});
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur=q.poll();

            for(int[] d:dir){
                int nr=cur[0]+d[0];
                int nc=cur[1]+d[1];

                if(nr<0||nc<0||nr>=n||nc>=n) continue;
                if(dist[nr][nc]!=-1) continue;

                dist[nr][nc]=dist[cur[0]][cur[1]]+1;
                q.offer(new int[]{nr,nc});
            }
        }

        int low=0,high=2*n;

        while(low<=high){

            int mid=(low+high)/2;

            if(canReach(mid,dist)){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }

        return high;
    }

    boolean canReach(int limit,int[][] dist){

        int n=dist.length;

        if(dist[0][0]<limit) return false;

        boolean[][] vis=new boolean[n][n];

        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{0,0});
        vis[0][0]=true;

        while(!q.isEmpty()){

            int[] cur=q.poll();

            if(cur[0]==n-1 && cur[1]==n-1)
                return true;

            for(int[] d:dir){

                int nr=cur[0]+d[0];
                int nc=cur[1]+d[1];

                if(nr<0||nc<0||nr>=n||nc>=n) continue;
                if(vis[nr][nc]) continue;
                if(dist[nr][nc]<limit) continue;

                vis[nr][nc]=true;
                q.offer(new int[]{nr,nc});
            }
        }

        return false;
    }
}
*/
//approach 3 multisource bfs + dsu 
/*
class Solution {

    int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};

    class DSU{

        int[] p;

        DSU(int n){
            p=new int[n];
            for(int i=0;i<n;i++)
                p[i]=i;
        }

        int find(int x){
            if(p[x]==x) return x;
            return p[x]=find(p[x]);
        }

        void union(int a,int b){

            int pa=find(a);
            int pb=find(b);

            if(pa!=pb)
                p[pa]=pb;
        }
    }

    public int maximumSafenessFactor(List<List<Integer>> grid){

        int n=grid.size();

        int[][] dist=new int[n][n];

        for(int[] row:dist)
            Arrays.fill(row,-1);

        Queue<int[]> q=new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid.get(i).get(j)==1){
                    dist[i][j]=0;
                    q.offer(new int[]{i,j});
                }
            }
        }

        while(!q.isEmpty()){

            int[] cur=q.poll();

            for(int[] d:dir){

                int nr=cur[0]+d[0];
                int nc=cur[1]+d[1];

                if(nr<0||nc<0||nr>=n||nc>=n) continue;
                if(dist[nr][nc]!=-1) continue;

                dist[nr][nc]=dist[cur[0]][cur[1]]+1;
                q.offer(new int[]{nr,nc});
            }
        }

        List<int[]> cells=new ArrayList<>();

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                cells.add(new int[]{dist[i][j],i,j});

        cells.sort((a,b)->b[0]-a[0]);

        boolean[][] active=new boolean[n][n];

        DSU dsu=new DSU(n*n);

        for(int[] cell:cells){

            int d=cell[0];
            int r=cell[1];
            int c=cell[2];

            active[r][c]=true;

            int id=r*n+c;

            for(int[] k:dir){

                int nr=r+k[0];
                int nc=c+k[1];

                if(nr<0||nc<0||nr>=n||nc>=n) continue;

                if(active[nr][nc])
                    dsu.union(id,nr*n+nc);
            }

            if(active[0][0] && active[n-1][n-1]
                    && dsu.find(0)==dsu.find(n*n-1))
                return d;
        }

        return 0;
    }
}*/