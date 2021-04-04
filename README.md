# mavericks-impure-function-PoC
Mavericks에는 debug모드에서 reducer가 순수함수인지 검사하는 기능이 있습니다. <br/>
[문서](https://airbnb.io/mavericks/#/debug-checks?id=debug-checks)를 보면 debug모드에선 이런 것들을 검사한다고 합니다.

> - Running all state reducers twice and ensuring that the resulting state is the same. This ensures that reducers are pure.
> - All state properties are immutable vals not vars.
> - State types are not one of: ArrayList, SparseArray, LongSparseArray, SparseArrayCompat, ArrayMap, and HashMap.
> - State class visibility is public so it can be created and restored
> - Each instance of your State is not mutated once it is set on the ViewModel

<br/>

이 부분을 위해 MavericksViewModel는 state를 update 하려고 할 때 <br/>
이 reducer를 두번 실행 해보고 그 결과값을 비교합니다.

```kotlin
// MavericksViewModel::setState
protected fun setState(reducer: S.() -> S) {
        if (config.debugMode) {
            stateStore.set {
                val firstState = this.reducer()
                val secondState = this.reducer()

                if (firstState != secondState) {
                    // 오류 감지
                }   
            }
        }
        // ...
}
```

<br/>

그래서 이렇게 외부 요인을 참조하는 순수하지 않은 함수로 reducer를 구성하면 오류가 발생합니다. <br/>
참고 : [샘플 브랜치(https://github.com/JSpiner/mavericks-impure-function-PoC/tree/impure-reducer)](https://github.com/JSpiner/mavericks-impure-function-PoC/tree/impure-reducer)

```kotlin
    private val countMax = 5

    fun addCount() {
        // it will occurs error
        setState {
            copy(count = min(count + 1, countMax))
        }
    }
    
```

> 
