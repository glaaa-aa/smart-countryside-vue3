import { defineStore } from 'pinia';
import { store } from '/@/store';

interface AppState {
    changeUSerInfo: any
}

export const useChangeUSerStore = defineStore({
    id: 'dissension',
    state: (): AppState => ({
        changeUSerInfo: {},
    }),
    getters: {
        getChangeUSerInfo(): any {
            return this.changeUSerInfo;
        },
    },
    actions: {
        setChangeUSerInfo(params: any): void {
            this.changeUSerInfo = params;
        }
    },
});

export function useChangeUSerStoreWithOut() {
    return useChangeUSerStore(store);
}